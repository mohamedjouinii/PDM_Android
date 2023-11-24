package com.example.myapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdaptar extends RecyclerView.Adapter<VideoAdaptar.VideoViewHolder> {

    List<Video> videoList ;
    public VideoAdaptar(List<Video>videoList){
        this.videoList=videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_video , parent ,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdaptar.VideoViewHolder holder, int position) {
        holder.setVideoData(videoList.get(position));

        holder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.back.setVisibility(View.GONE);
                ((Activity) v.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        ImageView back , like, pause;
        TextView tittre , description;


        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.VideoView);
            tittre = itemView.findViewById(R.id.titre);
            description=itemView.findViewById(R.id.video_description);
            back  = itemView.findViewById(R.id.backButton);
            like  = itemView.findViewById(R.id.like);
            pause  = itemView.findViewById(R.id.pause);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Video currentVideo = videoList.get(position);
                        boolean newLikeState = !currentVideo.isLiked(); // Inverser l'état actuel
                        currentVideo.setLiked(newLikeState);
                        notifyItemChanged(position); // Rafraîchir la vue
                    }
                }
            });
            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Video currentVideo = videoList.get(position);

                        if (currentVideo.isPlaying()) {
                            videoView.pause();
                        } else {
                            videoView.start();
                        }
                        currentVideo.setPlaying(!currentVideo.isPlaying());

                    }
                }
            });
        }



        public void setVideoData(Video video) {
            tittre.setText(video.getTitre());
            description.setText(video.getDescription());
            like.setImageResource(video.isLiked() ? R.drawable.like_liked : R.drawable.like);
            pause.setImageResource(video.isPlaying() ? R.drawable.pause : R.drawable.play);

            videoView.setVideoPath(video.getVideo());
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth() / (float)  videoView.getHeight();
                    float scale =videoRatio / screenRatio ;
                    if (scale >=1f ){
                        videoView.setScaleX(scale);
                    }
                    else {
                        videoView.setScaleY(1f/scale);
                    }
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}