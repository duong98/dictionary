import java.util.Objects;

public class Word {
    private String word;
    private String meaning;
    private boolean isFavorite = false;
    private String category;

    public Word(String word, String meaning, String category) {
        this.word = word;
        this.meaning = meaning;
        this.category = category;
    }

    public Word(String word, String meaning, boolean isFavorite, String category) {
        this.word = word;
        this.meaning = meaning;
        this.isFavorite = isFavorite;
        this.category = category;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return word + "---" + meaning + "---" + (isFavorite ? "1" : "0")
                + "---" + category + "\n";
    }

    public Word fromString(String line) {
        String[] tokens = line.split("---");
        boolean isFavorite = tokens[2].equals("1");
        return new Word(tokens[0].trim(), tokens[1].trim(),
                isFavorite, tokens[3].trim());
    }
}