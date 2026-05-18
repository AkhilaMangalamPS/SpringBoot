package org.example.phase2.model;

public class Task {
    private Long id;
    private String title;
    private boolean status;

    public Task(Long id,String title,boolean status){
        this.id = id;
        this.title = title;
        this.status = status;
    }

    protected Task(){}

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return  title;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return status;
    }
}
