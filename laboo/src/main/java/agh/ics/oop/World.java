package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import static agh.ics.oop.OptionsParser.change;

public class World {

    public static void main(String[] args){

        System.out.println("Start");
//        run(args);
        MoveDirection[] changed_arg = change(args);
        run(changed_arg);
        System.out.println("Stop");
    }

    static void run(MoveDirection[] args) {
        int i = 0;
        while (i < args.length) {
            switch (args[i]) {
                case FORWARD -> System.out.print("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.print("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.print("Zwierzak skręca w prawo");
                case LEFT -> System.out.print("Zwierzak skręca w lewo");
            }

            if (i != args.length - 1) {
                System.out.print(", ");
            }
            i += 1;
        }
        System.out.println();
    }

}
