package co.wander.healthy;

class MainItem {

    private final int Id;
    private final int imgId;
    private final int textId;
    private final int colorValue;


    MainItem(int Id, int imgId, int textId, int colorValue) {
        this.Id = Id;
        this.imgId = imgId;
        this.textId = textId;
        this.colorValue = colorValue;

    }

    public int getId() {
        return Id;
    }

    public int getImgId() {
        return imgId;
    }

    public int getTextId() {
        return textId;
    }

    public int getColorValue() {
        return colorValue;
    }
}
