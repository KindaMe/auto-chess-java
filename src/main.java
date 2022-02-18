import java.util.ArrayList;

public class main
{
    public static void main(String[] args)
    {
        //---- DEBUG ----//
        player player1 = new player();//create test player

        addPawnsTest(player1);
        equipTest(player1);
        engageTest(player1);
        mergeTest(player1);
        shopTest(player1);
        //---- DEBUG ----//
    }

    static void equipTest(player player1)
    {
        //---- DEBUG ----//
        System.out.println("---- BEFORE EQUIP ----");
        player1.debugLists();
        player1.equipPawn(1);
        player1.equipPawn(2);
        player1.equipPawn(1);
        player1.equipPawn(4);
        System.out.println("----------------------");

        System.out.println("---- AFTER EQUIP ----");
        player1.debugLists();
        System.out.println("---------------------");
    }

    static void mergeTest(player player1)
    {
        //---- DEBUG ----//
        System.out.println("---- BEFORE MERGE ----");
        player1.debugLists();
        System.out.println("----------------------");

        System.out.println("---- AFTER MERGE ----");
        player1.merge();
        player1.debugLists();
        System.out.println("---------------------");
    }

    static void addPawnsTest(player player1)
    {
        //---- DEBUG ----//
        player1.addPawn(new pawn3());
        player1.addPawn(new pawn2());
        player1.addPawn(new pawn1());
        player1.addPawn(new pawn1());
        player1.addPawn(new pawn2());
        player1.addPawn(new pawn3());
        player1.addPawn(new pawn2());
        player1.addPawn(new pawn3());
        player1.addPawn(new pawn1());
    }

    static void engageTest(player player1)
    {
        //---- DEBUG ----//
        System.out.println("---- ENGAGE ----");
        player1.playerBoard.get(0).engage();
        player1.playerBoard.get(0).engage();
        player1.playerBoard.get(0).engage();
        player1.playerBoard.get(0).engage();
        player1.playerBoard.get(0).engage();
        player1.playerBoard.get(0).engage();
        System.out.println("----------------");
    }

    static void shopTest(player player1)
    {
        //---- DEBUG ----//
        System.out.println("---- BEFORE REFRESH ----");
        player1.playerShop.debugShop();
        System.out.println("------------------------");

        System.out.println("---- AFTER REFRESH ----");
        player1.playerShop.refreshShop();
        player1.playerShop.debugShop();
        System.out.println("-----------------------");

        System.out.println("---- BEFORE BUY ----");
        player1.playerShop.debugShop();
        System.out.println("--------------------");

        System.out.println("---- AFTER BUY ----");
        player1.playerShop.buyPawn(1);
        player1.playerShop.debugShop();
        System.out.println("-------------------");
    }
}
