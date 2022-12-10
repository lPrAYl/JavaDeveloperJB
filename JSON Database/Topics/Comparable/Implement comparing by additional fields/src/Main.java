class Article implements Comparable<Article> {
    private String title;
    private int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int compareTo(Article otherArticle) {
        // add your code here!
        if (size == otherArticle.size) {
            return title.compareTo(otherArticle.title);
        }
        return Integer.compare(size, otherArticle.size);
    }
}