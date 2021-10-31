package design.copy;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.copy
 * @descripation 原型模式，复用对象， 对应cloneable 浅/深复用
 * @date 2021-07-16
 */
public class Student implements Cloneable{
    private int id;
    private String name;
    private int score;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
