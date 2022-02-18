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
    }

    void merge()
    {
        ArrayList<Comparator> mergeList = new ArrayList<Comparator>();

        fillMergeList(mergeList);

        int i = 0, j;
        while (i < mergeList.size() && mergeList.size() >= 3)
        {
            j = i + 1;

            mergeCheck(mergeList, i, j);

            i++;
        }
    }

    void mergeCheck(ArrayList<Comparator> mergeList, int i, int j)
    {
        int k;
        while (j < mergeList.size())
        {
            if (mergeList.get(i).match(mergeList.get(j)))
            {
                k = j + 1;
                while (k < mergeList.size())
                {
                    if (mergeList.get(i).match(mergeList.get(k)))
                    {
                        mergeList.get(i).getPawn().tierUp();
                        mergeList.get(k).destroyPawn();
                        mergeList.get(j).destroyPawn();

                        fillMergeList(mergeList);
                        return;
                    }
                    else
                    {
                        k++;
                    }
                }
                return;
            }
            else
            {
                j++;
            }
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

