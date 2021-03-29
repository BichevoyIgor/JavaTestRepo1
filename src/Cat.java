
public class Cat {

    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @BeforeSuite
    public void awake() {
        System.out.println("Кот по имени " + name + " проснулся");
    }

    @Test(priority = 4)
    public void jump() {
        System.out.println("Кот по имени " + name + " прыгнул");
    }

    @Test(priority = 2)
    public void run() {
        System.out.println("Кот по имени " + name + " побежал");
    }

    @AfterSuite
    public void sleep() {
        System.out.println("Кот по имени " + name + " уснул");
    }

}
