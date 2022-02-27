import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Menu extends JFrame
{
    Player player;
    HeaderPanel headerPanel;
    SidebarPanel sidebarPanel;
    ///Center panel
    CenterPanel centerPanel;
    ManagePanel managePanel;
    UnitStatsPanel unitStatsPanel;

    Menu(Player player)
    {
        this.player = player;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setSize(1920, 1080);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //// ---- Top Panel ----////
        headerPanel = new HeaderPanel();
        headerPanel.headerUpdate("temporary header");
        this.add(headerPanel, BorderLayout.NORTH);
        ////---- -------- ----////

        ////---- Left Panel ----/////
        sidebarPanel = new SidebarPanel();
        this.add(sidebarPanel, BorderLayout.WEST);
        ////---- -------- ----////

        ////---- Center Panel ----////
        centerPanel = new CenterPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        ////---- -------- ----////

        this.setVisible(true);
    }

    class HeaderPanel extends JPanel
    {
        JLabel headerLabel;

        HeaderPanel()
        {
            this.setLayout(new GridBagLayout());
            this.setBackground(Color.darkGray);
            this.setPreferredSize(new Dimension(0, 100));

            headerLabel = new JLabel();
            headerLabel.setHorizontalAlignment(JLabel.CENTER);
            headerLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
            headerLabel.setForeground(new Color(0xfdf800));

            this.add(headerLabel);
        }

        void headerUpdate(String title)
        {
            this.headerLabel.setText(title.toUpperCase());
        }
    }

    class SidebarPanel extends JPanel
    {
        ButtonGroup northButtonGroup;
        SidebarButton buttonFight;
        SidebarButton buttonManage;
        SidebarButton buttonList;
        //
        SidebarButton buttonExit;

        SidebarPanel()
        {
            this.setBackground(Color.gray);
            this.setPreferredSize(new Dimension(100, 0));
            this.setLayout(new BorderLayout());
            //this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel sidebarNorth = new JLabel();
            sidebarNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            sidebarNorth.setPreferredSize(new Dimension(100, 500));

            buttonFight = new SidebarButton("FIGHT");
            buttonFight.addActionListener(actionFight);

            buttonManage = new SidebarButton("MANAGE");
            buttonManage.addActionListener(actionManage);

            buttonList = new SidebarButton("LIST");
            buttonList.addActionListener(actionList);

            northButtonGroup = new ButtonGroup();
            northButtonGroup.add(buttonFight);
            northButtonGroup.add(buttonManage);
            northButtonGroup.add(buttonList);

            sidebarNorth.add(buttonFight);
            sidebarNorth.add(buttonManage);
            sidebarNorth.add(buttonList);

            JLabel sidebarSouth = new JLabel();
            sidebarSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            sidebarSouth.setPreferredSize(new Dimension(100, 100));

            buttonExit = new SidebarButton("EXIT");
            buttonExit.addActionListener(actionExit);

            sidebarSouth.add(buttonExit);

            this.add(sidebarNorth, BorderLayout.NORTH);
            this.add(sidebarSouth, BorderLayout.SOUTH);
        }

        ActionListener actionFight = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.removeAll();
                centerPanel.revalidate();
                centerPanel.repaint();
                ////
                JLabel fightTest = new JLabel("FIGHT", JLabel.CENTER);//debug
                fightTest.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
                centerPanel.add(fightTest);//debug
            }
        };
        ActionListener actionManage = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.removeAll();
                centerPanel.revalidate();
                centerPanel.repaint();
                ////
                if (managePanel == null)
                {
                    managePanel = new ManagePanel();
                }
                centerPanel.add(managePanel);
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        };
        ActionListener actionList = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.removeAll();
                centerPanel.revalidate();
                centerPanel.repaint();
                ////
                if (unitStatsPanel == null)
                {
                    unitStatsPanel = new UnitStatsPanel();
                }
                centerPanel.add(unitStatsPanel);
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        };
        ActionListener actionExit = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        };

        class SidebarButton extends JToggleButton
        {
            SidebarButton(String name)
            {
                this.setText(name);
                this.setPreferredSize(new Dimension(90, 90));
                this.setFocusable(false);

                this.setBackground(new Color(0xfdf800));
                this.setFont(new Font("Haettenschweiler", Font.PLAIN, 30));
                this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            }
        }
    }

    class CenterPanel extends JPanel
    {
        CenterPanel()
        {
            this.setLayout(new GridLayout());
            this.setBackground(Color.lightGray);
            this.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, new Color(0xfdf800)));
        }
    }

    class ManagePanel extends JPanel
    {
        enum titles
        {shop, board, bench}

        PawnsLabel board;
        PawnsLabel bench;
        PawnsLabel shop;

        ManagePanel()
        {
            this.setLayout(new FlowLayout());
            this.setOpaque(false);

            board = new PawnsLabel(titles.board);
            bench = new PawnsLabel(titles.bench);
            shop = new PawnsLabel(titles.shop);

            this.add(board);
            this.add(bench);
            this.add(shop);
        }

        void updateAll()
        {
            board.updatePawns();
            bench.updatePawns();
            shop.updatePawns();
        }

        class PawnsLabel extends JLabel
        {
            titles title;
            int maxSize;
            ArrayList<Pawn> pawnsList;
            TitleLabel titleLabel;
            ArrayList<SlotButton> slotButtons;

            PawnsLabel(titles title)
            {
                this.title = title;

                switch (title)
                {
                    case board -> {
                        maxSize = player.boardMax;
                        pawnsList = player.playerBoard;
                    }
                    case bench -> {
                        maxSize = player.benchMax;
                        pawnsList = player.playerBench;
                    }
                    case shop -> {
                        maxSize = player.playerShop.shopMax;
                        pawnsList = player.playerShop.shop;
                    }
                }

                final int margin = 5;
                this.setLayout(new FlowLayout(FlowLayout.LEFT, margin, margin));
                this.setPreferredSize(new Dimension(10 * 160 + 12 * margin, 160 + 4 + 2 * margin));
                this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                this.setBackground(Color.gray);
                this.setOpaque(true);

                titleLabel = new TitleLabel();
                this.add(titleLabel);

                slotButtons = new ArrayList<>();

                for (int i = 0; i < maxSize; i++)
                {
                    slotButtons.add(new SlotButton());
                    slotButtons.get(i).addActionListener(actionManage);
                    this.add(slotButtons.get(i));
                }

                updatePawns();
            }

            void updatePawns()
            {
                titleLabel.updateLabelText();
                for (int i = 0; i < maxSize; i++)
                {
                    if (i < pawnsList.size())
                    {
                        slotButtons.get(i).slotUpdate(pawnsList.get(i));
                    }
                    else
                    {
                        slotButtons.get(i).slotUpdate(null);
                    }
                }
            }

            ActionListener actionManage = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    SlotButton button = (SlotButton) e.getSource();

                    if (player.playerShop.shop.contains(button.pawn))
                    {
                        player.playerShop.buyPawn(button.pawn);
                        updateAll();
                    }
                    else if (player.playerBoard.contains(button.pawn))
                    {
                        player.unequipPawn(pawnsList.indexOf(button.pawn));
                        updateAll();
                    }
                    else if (player.playerBench.contains(button.pawn))
                    {
                        player.equipPawn(pawnsList.indexOf(button.pawn));
                        updateAll();
                    }
                }
            };

            class TitleLabel extends JLabel
            {
                TitleLabel()
                {
                    this.setLayout(new BorderLayout());
                    this.setPreferredSize(new Dimension(160, 160));
                    this.setBackground(new Color(0xfdf800));
                    this.setForeground(Color.black);
                    this.setFont(new Font("Haettenschweiler", Font.PLAIN, 30));
                    this.setHorizontalAlignment(JLabel.CENTER);

                    updateLabelText();

                    TitleButton titleButton = new TitleButton();

                    switch (title)
                    {
                        case board, bench -> titleButton.setVisible(false);
                        case shop -> titleButton.setText("REFRESH - " + player.playerShop.refreshCost + " GOLD");
                    }
                    titleButton.addActionListener(actionTitleSlot);

                    this.add(titleButton, BorderLayout.SOUTH);
                }

                void updateLabelText()//debug
                {
                    if (title == titles.shop)
                    {
                        this.setText(title.toString().toUpperCase() + " - " + player.playerShop.gold + " GOLD");
                    }
                    else
                    {
                        this.setText(title.toString().toUpperCase());
                    }
                }

                ActionListener actionTitleSlot = new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        if (title == titles.shop)
                        {
                            player.playerShop.refreshShop();
                            updateAll();
                        }
                    }
                };

                class TitleButton extends JButton
                {
                    TitleButton()
                    {
                        this.setFocusable(false);

                        this.setBackground(Color.black);
                        this.setForeground(new Color(0xfdf800));

                        this.setFont(new Font("Haettenschweiler", Font.PLAIN, 20));
                        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    }
                }
            }

            class SlotButton extends JButton
            {
                Pawn pawn;
                JPanel bottomPanel;
                SmallButton leftButton;

                SlotButton()
                {
                    this.setPreferredSize(new Dimension(160, 160));
                    this.setFocusable(false);
                    this.setOpaque(false);
                    this.setEnabled(false);

                    this.setBackground(new Color(0xfdf800));
                    this.setForeground(Color.black);
                    this.setFont(new Font("Haettenschweiler", Font.PLAIN, 30));
                    this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    ////debug
                    this.setLayout(new BorderLayout());
                    bottomPanel = new JPanel();
                    bottomPanel.setOpaque(false);
                    bottomPanel.setPreferredSize(new Dimension(0, 30));
                    bottomPanel.setLayout(new GridLayout());
                    bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                    leftButton = new SmallButton();
                    leftButton.addActionListener(actionShop);
                    bottomPanel.add(leftButton);
                    bottomPanel.setVisible(false);

                    this.add(bottomPanel, BorderLayout.SOUTH);
                    ////
                    this.setHorizontalAlignment(JButton.CENTER);
                    this.setVerticalAlignment(JButton.TOP);
                }

                class SmallButton extends JButton
                {
                    SmallButton()
                    {
                        this.setFocusable(false);

                        this.setBackground(Color.black);
                        this.setForeground(new Color(0xfdf800));

                        this.setFont(new Font("Haettenschweiler", Font.PLAIN, 20));
                        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    }

                    void updateButtonCost()
                    {
                        if (pawn != null)
                        {
                            switch (title)
                            {
                                case board, bench -> this.setText("SELL - " + pawn.currentCost + " GOLD");
                                case shop -> this.setText("BUY - " + pawn.currentCost + " GOLD");
                            }
                        }
                    }
                }

                ActionListener actionShop = new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {

                        if (player.playerShop.shop.contains(pawn))
                        {
                            player.playerShop.buyPawn(pawn);
                            updateAll();
                        }
                        else if (player.playerBoard.contains(pawn))
                        {
                            player.playerShop.sellPawn(pawn);
                            updateAll();
                        }
                        else if (player.playerBench.contains(pawn))
                        {
                            player.playerShop.sellPawn(pawn);
                            updateAll();
                        }
                    }
                };

                void slotUpdate(Pawn pawn)
                {
                    this.pawn = pawn;

                    leftButton.updateButtonCost();

                    if (pawn == null)
                    {
                        this.setText("EMPTY");
                        this.setOpaque(false);
                        this.setEnabled(false);
                        bottomPanel.setVisible(false);
                    }
                    else
                    {
                        this.setText("T" + pawn.tier + " " + pawn.name);
                        this.setOpaque(true);
                        this.setEnabled(true);
                        bottomPanel.setVisible(true);
                    }
                }
            }
        }
    }

    class UnitStatsPanel extends JPanel
    {
        UnitStatsPanel()
        {
            this.setOpaque(false);
            this.setLayout(new BorderLayout());
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            ArrayList<Pawn> statsList = new ArrayList<>();
            statsList.add(new Pawn1());
            statsList.add(new Pawn2());
            statsList.add(new Pawn3());
            statsList.add(new Pawn4());

            String[] columnNames = {"NAME", "HEALTH", "MANA", "DAMAGE", "COST"};

            Object[][] data = new Object[statsList.size()][columnNames.length];

            for (int i = 0; i < statsList.size(); i++)
            {
                data[i][0] = statsList.get(i).name;
                data[i][1] = statsList.get(i).defaultHealth;
                data[i][2] = statsList.get(i).defaultMana;
                data[i][3] = statsList.get(i).defaultDamage;
                data[i][4] = statsList.get(i).defaultCost;
            }

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
            {
                @Override
                public boolean isCellEditable(int row, int column)//disables editing cells
                {
                    return false;
                }

                @Override
                public Class getColumnClass(int column)//makes row sorter actually work
                {
                    switch (column)
                    {
                        case 1, 2, 3, 4 -> {

                            return Integer.class;
                        }
                        default -> {
                            return String.class;
                        }
                    }
                }
            };

            JTable table = new JTable(tableModel);
            table.setFillsViewportHeight(true);
            table.setBackground(Color.lightGray);
            table.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
            table.setRowHeight(table.getRowHeight() + 40);
            table.setGridColor(Color.black);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setAutoCreateRowSorter(true);

            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);
            table.getTableHeader().setFont(new Font("Haettenschweiler", Font.PLAIN, 50));
            table.getTableHeader().setBackground(Color.darkGray);
            table.getTableHeader().setForeground(new Color(0xfdf800));

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            scrollPane.getVerticalScrollBar().setBackground(Color.darkGray);
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI()
            {
                @Override
                protected void configureScrollBarColors()
                {
                    this.thumbColor = new Color(0xfdf800);
                }
            });

            scrollPane.getVerticalScrollBar().getComponent(0).setBackground(new Color(0xfdf800));
            scrollPane.getVerticalScrollBar().getComponent(1).setBackground(new Color(0xfdf800));
            JPanel cornerColorPanel = new JPanel();
            cornerColorPanel.setBackground(Color.darkGray);
            scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, cornerColorPanel);

            this.add(scrollPane, BorderLayout.CENTER);
        }
    }
}

