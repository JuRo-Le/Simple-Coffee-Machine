/**
 * 
 */
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Le
 *
 */
class CoffeeMachine {
	
	private static final String[] tasks = new String[7];
    private static final int WATER = 200;
    private static final int MILK = 50;
    private static final int COFFEE_BEANS = 15;
    private static final int W_I = 0;
    private static final int M_I = 1;
    private static final int CB_I = 2;
    private static final int CC_I = 3;
	private static final String BUY = "buy";
	private static final String FILL = "fill";
	private static final String TAKE = "take";
	private static final String REMAINING = "remaining";
	private static final String EXIT = "exit";
	private static final int E_WATER = 250;
    private static final int E_MILK = 0;
    private static final int E_COFFEE_BEANS = 16;
	private static final int E_COSTS = 4;
	private static final int L_WATER = 350;
    private static final int L_MILK = 75;
    private static final int L_COFFEE_BEANS = 20;
	private static final int L_COSTS = 7;
	private static final int C_WATER = 200;
    private static final int C_MILK = 100;
    private static final int C_COFFEE_BEANS = 12;
	private static final int C_COSTS = 6;
	private static final String BACK = "back";
    

    private static Scanner sc = new Scanner(System.in);
    private static int[] ingridient = new int[4];
	private static int totalMoney = 550;
	private static int remainWater = 400;
	private static int remainMilk = 540;
	private static int remainCoffeeBeans = 120;
	private static int remainDisposableCups = 9;
    

    private static void createTasks() {
        tasks[0] = "Starting to make a coffee";
        tasks[1] = "Grinding coffee beans";
        tasks[2] = "Boiling water";
        tasks[3] = "Mixing boiled water with crushed coffee beans";
        tasks[4] = "Pouring coffee into the cup";
        tasks[5] = "Pouring some milk into the cup";
        tasks[6] = "Coffee is ready!";
    }
    
    private static void showTask(int i){
        System.out.println(tasks[i]);
    }

    private static void showAllTask() {
        for (int i = 0; i < tasks.length; i++){
            showTask(i);
        }
    }

    private static void defineIngridient() {
        System.out.println("Write how many cups of coffee you will need:");
        int coffeeCups = sc.nextInt();
        int waterUse = WATER * coffeeCups;
        int milkUse = MILK * coffeeCups;
        int coffeeBeansUse = COFFEE_BEANS * coffeeCups;
        System.out.printf("For %d cups of coffee you will need:\n"
        + "%d ml of water\n"
		+ "%d ml of milk\n"
        + "%d g of coffee beans\n", coffeeCups, waterUse, milkUse, coffeeBeansUse);
		System.out.println();
    }

    private static void importIngridient() {
        final String[] imports = new String[4];
        imports[0] = "Write how many ml of water the coffee machine has:";
        imports[1] = "Write how many ml of milk the coffee machine has:";
        imports[2] = "Write how many grams of coffee beans the coffee machine has:";
        imports[3] = "Write how many cups of coffee you will need:";
        for (int i = 0; i < ingridient.length; i++) {
            System.out.println(imports[i]);
            ingridient[i] = sc.nextInt();
        }
    }

    private static void calCoffeeCups() {
        int cupsByWater = ingridient[W_I] / WATER;
        int cupsByMilk = ingridient[M_I] / MILK;
        int cupsByCoffeeBeans = ingridient[CB_I] / COFFEE_BEANS;
        int maxCoffeeCups = cupsByWater < cupsByMilk ? cupsByWater : cupsByMilk;
        maxCoffeeCups = cupsByCoffeeBeans < maxCoffeeCups ? cupsByCoffeeBeans : maxCoffeeCups;

        int remainCups = maxCoffeeCups - ingridient[CC_I];
        if (remainCups == 0) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (remainCups > 0) {
            System.out.println("Yes, I can make that amount of coffee (and even " + remainCups 
                               + " more than that)");
        } else {
            System.out.println("No, I can make only " + maxCoffeeCups + " cup(s) of coffee");
        }
		System.out.println();
    }
	
	private static void showCurrentMachineStage() {
		System.out.printf("The coffee machine has:\n" 
		+ "%d ml of water\n"
        + "%d ml of milk\n"
        + "%d g of coffee beans\n"
		+ "%d disposable cups\n"
		+ "$%d of money\n", remainWater, remainMilk, remainCoffeeBeans, remainDisposableCups, totalMoney);
		System.out.println();
	}
	
	private static void showMenu() {
		System.out.println("Write action (buy, fill, take, remaining, exit):");
		String selectAction = sc.nextLine();
		for ( ; ; ) {
			if (BUY.equalsIgnoreCase(selectAction)) {
				buyCoffee();
			} else if (FILL.equalsIgnoreCase(selectAction)) {
				fillCoffeeMachine();
			} else if (TAKE.equalsIgnoreCase(selectAction)) {
				takeAllMoney();
			} else if (REMAINING.equalsIgnoreCase(selectAction)) {
				showCurrentMachineStage();
			} else if (EXIT.equalsIgnoreCase(selectAction)) {
				break;
			}
			System.out.println();
			System.out.println("Write action (buy, fill, take, remaining, exit):");
			selectAction = sc.nextLine();
		}
	}
	
	private static void buyCoffee() {
		System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
		String buyOption = sc.nextLine();
		
		if (BACK.equalsIgnoreCase(buyOption)) {
			return;
		} else {
			switch (Integer.parseInt(buyOption)) {
				case 1:
					makeEspresso();
					break;
				case 2:
					makeLatte();
					break;
				case 3:
					makeCappuccino();
					break;
				default:
					break;
			}
		}
	}
	
	private static void showText(String text) {
		System.out.println("Sorry, not enough " + text);
	}
	
	private static void makeEspresso() {
		String check = "";
		if (remainWater < E_WATER) {
			check = "water!";
		} else if (remainCoffeeBeans < E_COFFEE_BEANS) {
			check = "beans!";
		} else if (remainDisposableCups == 0) {
			check = "disposable cups!";
		} else {
			System.out.println("I have enough resources, making you a coffee!");
			remainWater -= E_WATER;
			remainMilk -= E_MILK;
			remainCoffeeBeans -= E_COFFEE_BEANS;
			remainDisposableCups -= 1;
			totalMoney += E_COSTS;
			return;
		}
		showText(check);
		return;
	}
	
	private static void makeLatte() {
		String check = "";
		if (remainWater < L_WATER) {
			check = "water!";
		} else if (remainMilk < L_MILK) {
			check = "milk!";
		} else if (remainCoffeeBeans < L_COFFEE_BEANS) {
			check = "beans!";
		} else if (remainDisposableCups == 0) {
			check = "disposable cups!";
		} else {
			System.out.println("I have enough resources, making you a coffee!");
			remainWater -= L_WATER;
			remainMilk -= L_MILK;
			remainCoffeeBeans -= L_COFFEE_BEANS;
			remainDisposableCups -= 1;
			totalMoney += L_COSTS;
			return;
		}
		showText(check);
		return;
	}
	
	private static void makeCappuccino() {
		String check = "";
		if (remainWater < C_WATER) {
			check = "water!";
		} else if (remainMilk < C_MILK) {
			check = "milk!";
		} else if (remainCoffeeBeans < C_COFFEE_BEANS) {
			check = "beans!";
		} else if (remainDisposableCups == 0) {
			check = "disposable cups!";
		} else {
			System.out.println("I have enough resources, making you a coffee!");
			remainWater -= C_WATER;
			remainMilk -= C_MILK;
			remainCoffeeBeans -= C_COFFEE_BEANS;
			remainDisposableCups -= 1;
			totalMoney += C_COSTS;
			return;
		}
		showText(check);
		return;
	}
	
	private static void fillCoffeeMachine() {
		System.out.println("Write how many ml of water you want to add:");
		remainWater += sc.nextInt();
		System.out.println("Write how many ml of milk you want to add:");
		remainMilk += sc.nextInt();
		System.out.println("Write how many grams of coffee beans you want to add:");
		remainCoffeeBeans += sc.nextInt();
		System.out.println("Write how many disposable cups you want to add:");
		remainDisposableCups += sc.nextInt();
	}
	
	private static void takeAllMoney() {
		System.out.println("I gave you $" + totalMoney);
		totalMoney = 0;
	}

    public static void main(String[] args) {
		showMenu();
    }
}