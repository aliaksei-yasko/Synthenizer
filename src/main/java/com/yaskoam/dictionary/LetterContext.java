package com.yaskoam.dictionary;

/**
 * @author Q-YAA
 */
public class LetterContext {

    private String letter;

    private String rightContext;

    private String leftContext;

    public LetterContext(String letterContext) {
        parseLetterContext(letterContext);
    }

    public boolean isContextForLetter(String letter) {
        return this.letter.equals(letter);
    }

    public String getLetter() {
        return letter;
    }

    public String getRightContext() {
        return rightContext;
    }

    public String getLeftContext() {
        return leftContext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LetterContext)) {
            return false;
        }

        LetterContext context = (LetterContext) o;

        if (leftContext != null ? !leftContext.equals(context.leftContext) : context.leftContext != null) {
            return false;
        }
        if (letter != null ? !letter.equals(context.letter) : context.letter != null) {
            return false;
        }
        if (rightContext != null ? !rightContext.equals(context.rightContext) : context.rightContext != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = letter != null ? letter.hashCode() : 0;
        result = 31 * result + (rightContext != null ? rightContext.hashCode() : 0);
        result = 31 * result + (leftContext != null ? leftContext.hashCode() : 0);
        return result;
    }

    private void parseLetterContext(String letterContext) {
        letter = letterContext.split("\\|")[1];
        leftContext = letterContext.split("\\|")[0];
        rightContext = letterContext.split("\\|")[2];
    }
}
