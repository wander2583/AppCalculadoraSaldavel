 package co.wander.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<MainItem> mainItems = new ArrayList<>();
        mainItems.add(new MainItem(1, R.drawable.ic_tag_faces_black_24dp, R.string.imc, 0xFF87CEEB));
        mainItems.add(new MainItem(2, R.drawable.ic_child_care_black_24dp, R.string.tmb, 0XFF6A5ACD));
        mainItems.add(new MainItem(3, R.drawable.ic_accessibility_black_24dp, R.string.pgo, 0XFF483D8B));
        mainItems.add(new MainItem(4, R.drawable.ic_touch_app_black_24dp, R.string.tbc, 0XFF6495ED));


        MainAdapter adapter = new MainAdapter(this, mainItems);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private class MainAdapter extends RecyclerView.Adapter<MainViewHolder>
    implements MainViewHolder.OnItemClickListener {

        private final List<MainItem> mainItems;
        private final MainActivity activity;

        MainAdapter(MainActivity activity, List<MainItem> mainItems) {
            this.activity = activity;
            this.mainItems = mainItems;
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.item_main, viewGroup, false);
            return new MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int position) {
            MainItem mainItem = this.mainItems.get(position);
            mainViewHolder.bind(mainItem, this);

        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }

        @Override
        public void onClick(int position) {
            MainItem mainItem = this.mainItems.get(position);
            switch (mainItem.getId()) {
                case 1: {
                    Intent intent = new Intent(activity, ImcActivity.class);
                    activity.startActivity(intent);
                } break;
                case 2: {
                    Intent intent = new Intent(activity, TmbActivity.class);
                    activity.startActivity(intent);
                } break;
                case 3: {
                    Intent intent = new Intent(activity, PgoActivity.class);
                    activity.startActivity(intent);
                } break;
                case 4: {
                    Intent intent = new Intent(activity, TbcActivity.class);
                    activity.startActivity(intent);
                } break;

            }
        }
    }

    private static class MainViewHolder extends RecyclerView.ViewHolder {

        interface OnItemClickListener {
            void onClick(int position);
        }

        private final ImageView imgViewMain;
        private final TextView imgViewtext;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewMain = itemView.findViewById(R.id.item_main_img);
            imgViewtext = itemView.findViewById(R.id.item_main_text);

        }

        void bind(MainItem mainItem, final OnItemClickListener listener) {
            itemView.setBackgroundColor(mainItem.getColorValue());
            imgViewMain.setImageResource(mainItem.getImgId());
            imgViewtext.setText(mainItem.getTextId());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());

                }
            });

        }
    }
}
