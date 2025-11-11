package tests;

public enum EnumSample {
    MONDAY(101,"1"),
    TUESDAY(102,"2"),
    WEDNESDAY(103,"3"),
    THURSDAY(104,"4"),
    FRIDAY(105,"5"),
    SATURDAY(106,"6");

    private int val;
    private String comment;

    EnumSample(int val,String comment){
        this.val = val;
        this.comment = comment ;
    }

    public int getVal(){
        return val;
    }
    public String getComment(){
        return comment;
    }
}
