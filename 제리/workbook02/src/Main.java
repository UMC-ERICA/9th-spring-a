public interface CoffeeBeans {
    void grind();
}

@Component("regularBean")
public static class RegularCoffeeBeans implements CoffeeBeans {
    @Override
    public void grind() {
        System.out.println("기본 원두로 갈고 있습니다");
    }
}

@Component("decafBean")
public static class DecafCoffeeBeans implements CoffeeBeans {

    @Override
    public void grind() {
        System.out.println("디카페인 원두로 갈고 있습니다");
    }
}

@Component
public static class CoffeeMachine {

    @Autowired
    @Qualifier("decafBean")
    private CoffeeBeans coffeeBeans;

    public void brew() {
        coffeeBeans.grind();
        System.out.println("원두로 커피를 내립니다.");
    }
}