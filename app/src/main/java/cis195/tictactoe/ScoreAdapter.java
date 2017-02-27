package cis195.tictactoe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by arelin on 2/27/17.
 */
public class ScoreAdapter extends
        RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private Map<String, ?> _scores;
    private Context _context;

    public ScoreAdapter(Context context, Map<String, ?> scores) {
        _scores = scores;
        _context = context;
    }

    private Context getContext() {
        return _context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.score_layout, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String scorer = (String) _scores.keySet().toArray()[position];
        int score = (Integer) _scores.get(scorer);
        TextView textView = holder.nameTextView;
        textView.setText(scorer);
        TextView scoreView = holder.scoreTextView;
        scoreView.setText(Integer.toString(score));

    }

    @Override
    public int getItemCount() {
        return _scores.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView scoreTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.scorer_name);
            scoreTextView = (TextView) itemView.findViewById(R.id.score);
        }
    }
}
