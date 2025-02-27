package com.example.ultai.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ultai.R;
import com.example.ultai.databinding.FragmentNewsBinding;
import com.example.ultai.news.newsapp.adapter.NewsAdapter;
import com.example.ultai.news.newsapp.model.NewsItem;
import com.example.ultai.news.newsapp.utils.RssParser;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(newsAdapter);

        // Инициализация ViewModel
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        final TextView textView = binding.textView4;
        newsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Навигация по нажатию на кнопку
        binding.imageButton7.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_navigation_news_to_profileFragment);
        });

        // Запуск AsyncTask для загрузки новостей
        new FetchNewsTask().execute();
    }

    private class FetchNewsTask extends AsyncTask<Void, Void, List<NewsItem>> {

        @Override
        protected List<NewsItem> doInBackground(Void... voids) {
            List<NewsItem> allNews = new ArrayList<>();

            // Forbes
            allNews.addAll(RssParser.fetchNews("https://www.forbes.com/news/feed"));

            // РБК
            allNews.addAll(RssParser.fetchNews("https://www.rbc.ru/export.xml"));

            return allNews;
        }

        @Override
        protected void onPostExecute(List<NewsItem> allNews) {
            // Обновление адаптера
            newsAdapter = new NewsAdapter(getContext(), allNews);
            recyclerView.setAdapter(newsAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}