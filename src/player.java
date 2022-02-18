import java.util.ArrayList;

class Comparator
{
    ArrayList<pawn> source;
    int index;
    Class pawnClass;
    int tier;

    Comparator(ArrayList<pawn> source, int index)
    {
        this.source = source;
        this.index = index;

        pawnClass = source.get(index).getClass();
        tier = source.get(index).tier;
    }

    boolean match(Comparator compareTo)
    {
        if (pawnClass == compareTo.pawnClass && tier == compareTo.tier)
        {
            return true;
        }
        return false;
    }

    public pawn getPawn()
    {
        return source.get(index);
    }

    void destroyPawn()
    {
        source.remove(index);
    }
}

public class player
{
    shop playerShop = new shop(this);
    final int boardMax = 7;
    ArrayList<pawn> playerBoard;
    final int benchMax = 9;
    ArrayList<pawn> playerBench;

    player()
    {
        playerBoard = new ArrayList<pawn>();
        playerBench = new ArrayList<pawn>();
    }

    void addPawn(pawn toAdd)
    {
        if (playerBench.size() < benchMax)
        {
            playerBench.add(toAdd);
        }
    }

    void equipPawn(int benchIndex)
    {
        if (playerBoard.size() < boardMax && benchIndex < playerBench.size())
        {
            playerBoard.add(playerBench.get(benchIndex));
            playerBench.remove(benchIndex);
        }
    }

    void unequipPawn(int boardIndex)
    {
        if (playerBench.size() < benchMax)
        {
            playerBench.add(playerBoard.get(boardIndex));
            playerBoard.remove(boardIndex);
        }
    }

    void merge()
    {

        ArrayList<Comparator> mergeList = new ArrayList<Comparator>();

        fillMergeList(mergeList);

        mergeCheck(mergeList);
    }

    void fillMergeList(ArrayList<Comparator> compareList)
    {
        compareList.clear();

        if (playerBench.size() + playerBoard.size() >= 3)
        {
            if (!playerBench.isEmpty())
            {
                for (int i = 0; i < playerBench.size(); i++)
                {
                    compareList.add(new Comparator(playerBench, i));
                }
            }
            if (!playerBoard.isEmpty())
            {
                for (int i = 0; i < playerBoard.size(); i++)
                {
                    compareList.add(new Comparator(playerBoard, i));
                }
            }
        }
        else
        {
            return;
        }
    }

    void mergeCheck(ArrayList<Comparator> compareList)
    {
        int i = 0, j, k;
        while (i < compareList.size() && compareList.size() >= 3)
        {
            j = i + 1;

            while (j < compareList.size())
            {
                if (compareList.get(i).match(compareList.get(j)))
                {
                    k = j + 1;
                    while (k < compareList.size())
                    {
                        if (compareList.get(i).match(compareList.get(k)))
                        {
                            compareList.get(i).getPawn().tierUp();
                            compareList.get(k).destroyPawn();
                            compareList.get(j).destroyPawn();

                            fillMergeList(compareList);
                            //return;
                        }
                        else
                        {
                            k++;
                        }
                    }
                }
                else
                {
                    j++;
                }
            }
            i++;
        }
    }

    void debugLists()
    {
        System.out.println("BENCH");
        for (pawn i : playerBench)
        {
            System.out.print(i.tier + i.name + " ");
        }
        System.out.println();

        System.out.println("BOARD");
        for (pawn i : playerBoard)
        {
            System.out.print(i.tier + i.name + " ");
        }
        System.out.println();
    }
}

