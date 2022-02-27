abstract class Pawn
{
    //---- default ----//
    String name;
    int defaultHealth;
    int defaultMana;
    int defaultDamage;
    int defaultCost;
    //---- init ----//
    int initHealth;
    int initMana;
    int initDamage;
    //---- current ----//
    int currentHealth;
    int currentMana;
    int currentDamage;
    int currentCost;
    int tier = 1;

    @Override
    public String toString()
    {
        return "Name:" + name + "|DefaultHealth:" + defaultHealth + "|DefaultMana:" + defaultMana + "|DefaultDamage:" + defaultDamage + "|";
    }

    void updateStats()
    {
        switch (tier)
        {
            case 1 -> {
                initHealth = defaultHealth;
                currentHealth = initHealth;
                initMana = defaultMana;
                currentMana = 0;
                initDamage = defaultDamage;
                currentCost = defaultCost;
            }
            case 2 -> {
                initHealth = defaultHealth * 2;
                currentHealth = initHealth;
                initMana = defaultMana / 2;
                currentMana = 0;
                initDamage = defaultDamage * 2;
                currentCost = defaultCost * 3;
            }
            case 3 -> {
                initHealth = defaultHealth * 3;
                currentHealth = initHealth;
                initMana = defaultMana / 3;
                currentMana = 0;
                initDamage = defaultDamage * 3;
                currentDamage = initDamage;
                currentCost = defaultCost * 9;
            }
        }
    }

    void tierUp()
    {
        tier++;
        updateStats();
    }

    void engage()
    {
        System.out.println(name.toUpperCase() + " ENGAGE - CURRENT MANA " + currentMana + " - MAX MANA " + initMana);
        passive();
        if (currentMana < initMana)
        {
            currentMana += 10;//placeholder
            attack();
        }
        else if (currentMana == initMana)
        {
            currentMana = 0;//placeholder
            ability();
        }
    }

    void attack()
    {
        System.out.println("PAWN ATTACK");
    }

    void ability()
    {
        System.out.println("PAWN ABILITY");
    }

    void passive()
    {
        System.out.println("PAWN PASSIVE");
    }

}

class Pawn1 extends Pawn
{
    Pawn1()
    {
        name = "Thanasis";
        defaultHealth = 100;
        defaultMana = 50;
        defaultDamage = 5;
        defaultCost = 1;
        updateStats();
    }

    @Override
    void attack()
    {
        System.out.println(name.toUpperCase() + " ATTACK");
    }

    @Override
    void ability()
    {
        System.out.println(name.toUpperCase() + " ABILITY");
    }

    @Override
    void passive()
    {
        System.out.println(name.toUpperCase() + " PASSIVE");
    }
}

class Pawn2 extends Pawn
{
    Pawn2()
    {
        name = "Korina";
        defaultHealth = 80;
        defaultMana = 40;
        defaultDamage = 10;
        defaultCost = 2;
        updateStats();
    }

    @Override
    void attack()
    {
        System.out.println(name.toUpperCase() + " ATTACK");
    }

    @Override
    void ability()
    {
        System.out.println(name.toUpperCase() + " ABILITY");
    }

    @Override
    void passive()
    {
        System.out.println(name.toUpperCase() + " PASSIVE");
    }
}

class Pawn3 extends Pawn
{
    Pawn3()
    {
        name = "Agapios";
        defaultHealth = 50;
        defaultMana = 60;
        defaultDamage = 25;
        defaultCost = 3;
        updateStats();
    }

    @Override
    void attack()
    {
        System.out.println(name.toUpperCase() + " ATTACK");
    }

    @Override
    void ability()
    {
        System.out.println(name.toUpperCase() + " ABILITY");
    }

    @Override
    void passive()
    {
        System.out.println(name.toUpperCase() + " PASSIVE");
    }
}

class Pawn4 extends Pawn
{
    Pawn4()
    {
        name = "Ermis";
        defaultHealth = 70;
        defaultMana = 0;
        defaultDamage = 10;
        defaultCost = 2;
        updateStats();
    }

    @Override
    void attack()
    {
        System.out.println(name.toUpperCase() + " ATTACK");
    }

    @Override
    void ability()
    {
        System.out.println(name.toUpperCase() + " ABILITY");
    }

    @Override
    void passive()
    {
        System.out.println(name.toUpperCase() + " PASSIVE");
    }
}