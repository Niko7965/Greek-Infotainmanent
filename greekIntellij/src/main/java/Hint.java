public class Hint {
    String content;
    boolean active;

    public Hint(String content, boolean active){
        this.content=content;
        this.active=active;
    }

    public String getContent() {
        return content;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
