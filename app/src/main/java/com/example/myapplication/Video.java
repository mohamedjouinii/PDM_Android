package com.example.myapplication;

class Video {
    private  String nom , prenom , type ,  video ,  titre , description ;
    public Video(String nom , String prenom , String titre , String type ,String video ,  String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.video = video;
        this.titre = titre;
        this.description = description;
    }

    public Video (String video,String titre , String description){
        this.video = video;
        this.titre = titre;
        this.description = description;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public String getTitre() {
        return titre;
    }


    public String getDescription() {
        return description;
    }
    private boolean liked;
    private boolean playing;

    // Constructeur, getters, setters

    public boolean isLiked() {
        return liked;
    }
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}