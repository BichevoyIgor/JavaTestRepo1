
public class Dog {

    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @BeforeSuite
    public void awake() {
        System.out.println("Пес по имени " + name + " проснулся");
    }

    @Test(priority = 4)
    public void jump() {
        System.out.println("Пес по имени " + name + " прыгнул");
    }

    @Test(priority = 6)
    public void run() {
        System.out.println("Пес по имени " + name + " побежал");
    }

    @AfterSuite
    public void sleep() {
        System.out.println("Пес по имени " + name + " уснул");
    }

}
