public class Main
{
    public static void main(String[] args)
    {
        //---- DEBUG ----//
        Player player1 = new Player();//create test Player

        addPawnsTest(player1);
        equipTest(player1);
        engageTest(player1);
        //mergeTest(player1);
        shopTest(player1);
        //---- DEBUG ----//

        new Menu(player1);
    }

    static void equipTest(Player player1)
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

    static void mergeTest(Player player1)
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

    static void addPawnsTest(Player player1)
    {
        //---- DEBUG ----//
        player1.addPawn(new Pawn3());
        player1.addPawn(new Pawn2());
        player1.addPawn(new Pawn1());
        player1.addPawn(new Pawn1());
        player1.addPawn(new Pawn2());
        player1.addPawn(new Pawn3());
        player1.addPawn(new Pawn2());
        player1.addPawn(new Pawn3());
        player1.addPawn(new Pawn1());
    }

    static void engageTest(Player player1)
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

    static void shopTest(Player player1)
    {
        //---- DEBUG ----//
        System.out.println("---- BEFORE REFRESH ----");
        player1.playerShop.debugShop();
        System.out.println("------------------------");

        System.out.println("---- AFTER REFRESH ----");
        player1.playerShop.refreshShop();
        player1.playerShop.debugShop();
        System.out.println("-----------------------");
    }
}
