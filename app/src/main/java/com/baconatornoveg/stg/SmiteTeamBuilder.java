package com.baconatornoveg.stg;


import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.RawRes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;


public class SmiteTeamBuilder {

	public SmiteTeamBuilder(Context context) {
		this.context = context;
	}


	private final String version = "1.0.0";

	public String getVersion() {
		return version;
	}
	private Context context;
	private File bootsCSV;
	private File godsCSV;
	private File itemsCSV;

	public ArrayList<String> player1Loadout;
	public ArrayList<String> player2Loadout;
	public ArrayList<String> player3Loadout;
	public ArrayList<String> player4Loadout;
	public ArrayList<String> player5Loadout;
	
	private final ArrayList<Mage> MAGES = new ArrayList<>();
	private final ArrayList<Guardian> GUARDIANS = new ArrayList<>();
    private final ArrayList<Warrior> WARRIORS = new ArrayList<>();
    private final ArrayList<Assassin> ASSASSINS = new ArrayList<>();
    private final ArrayList<Hunter> HUNTERS = new ArrayList<>();

    private ArrayList<Item> build = new ArrayList<>();

    private final ArrayList<Item> ITEMS = new ArrayList<>();
    private final ArrayList<Item> BOOTS = new ArrayList<>();
    
	private Random rand = new Random();
	

	private String[] typeRoll = {"Mage", "Guardian", "Warrior", "Assassin", "Hunter"};
	
	public void registerLists() {

		bootsCSV = new File("raw/boots.csv");
		godsCSV = new File("raw/gods.csv");
		itemsCSV = new File("raw/items.csv");

		Scanner in;


		//try {
			//Begin parsing the boots CSV file
			in = new Scanner((context.getResources().openRawResource(R.raw.boots)));

			//Skip header row
			in.nextLine();

			while (in.hasNextLine()) {

				String line = in.nextLine();
				String[] values = line.split(",");
				if (values[1].toUpperCase().equals("TRUE")) {
					BOOTS.add(new Item(true, false, values[0]));
				} else {
					BOOTS.add(new Item(false, true, values[0]));
				}
			}

			in.close();

		//} catch (IOException e) {
		//	e.printStackTrace();
		//}

		//Begin parsing the gods CSV file
		//try {
			//in = new Scanner(godsCSV);
			in = new Scanner((context.getResources().openRawResource(R.raw.gods)));

			//Skip header row
			in.nextLine();

			while (in.hasNextLine()) {

				String line = in.nextLine();
				String[] values = line.split(",");
				switch (values[1]) {

					case "Assassin":
						ASSASSINS.add(new Assassin(values[0]));
						break;

					case "Guardian":
						GUARDIANS.add(new Guardian(values[0]));
						break;

					case "Hunter":
						HUNTERS.add(new Hunter(values[0]));
						break;

					case "Mage":
						MAGES.add(new Mage(values[0]));
						break;

					case "Warrior":
						WARRIORS.add(new Warrior(values[0]));
						break;

				}
			}


			in.close();
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		//Begin parsing the items CSV file

		//try {
			//in = new Scanner(itemsCSV);
		in = new Scanner(context.getResources().openRawResource(R.raw.items));

			//Skip header row
			in.nextLine();

			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] values = line.split(",");

				if (values[1].toUpperCase().equals("TRUE") && (values[2].toUpperCase().equals("FALSE"))) {
					ITEMS.add(new Item(true, false, values[0]));
				} else if (values[1].toUpperCase().equals("FALSE") && (values[2].toUpperCase().equals("TRUE"))) {
					ITEMS.add(new Item(false, true, values[0]));
				} else {
					ITEMS.add(new Item(true, true, values[0]));
				}
			}

		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}

	public void generateTeam(int size) {
		try {
			resetLoadouts();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		shufflePositions(typeRoll);
		ArrayList<String> loadout;
		
		switch (size) {
		
		case 1:
			loadout = makePlayerLoadout(typeRoll[0]);
			player1Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			break;
			
		case 2:
			loadout = makePlayerLoadout(typeRoll[0]);
			player1Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[1]);
			player2Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			break;
			
		case 3:
			loadout = makePlayerLoadout(typeRoll[0]);
			player1Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[1]);
			player2Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[2]);
			player3Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			break;
			
		case 4:
			loadout = makePlayerLoadout(typeRoll[0]);
			player1Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[1]);
			player2Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[2]);
			player3Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[3]);
			player4Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			break;
			
		case 5:
			loadout = makePlayerLoadout(typeRoll[0]);
			player1Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[1]);
			player2Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[2]);
			player3Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[3]);
			player4Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			loadout = makePlayerLoadout(typeRoll[4]);
			player5Loadout = loadout;
			System.out.println(loadout.get(0) + ": " + loadout.get(1));
			break;
		}
		
	}

	private void resetLoadouts() {
		player1Loadout = null;
		player2Loadout = null;
		player3Loadout = null;
		player4Loadout = null;
		player5Loadout = null;
	}
	
	public void shufflePositions(String[] positions) {

        int n = positions.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(positions, i, change);
        }

    }
	
	private void swap(String[] a, int i, int change) {
        String helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }
	
	private ArrayList<String> makePlayerLoadout(String position) {
		String player = null;
		String playerBuild = null;
		
		switch (position) {
		
		case "Mage":
			player = MAGES.get(rand.nextInt(MAGES.size()-1)).toString();
			build = generateBuild("magical");
			while (true) {
				int offensiveCount = 0;
				for (Item i : build) {
					if (!i.isPhysical) {
						offensiveCount++;
					}
				}
				if (offensiveCount < 4) {
					build = generateBuild("magical");
				} else {
					break;
				}
			}
			playerBuild = build.toString();
			break;
			
		case "Guardian":
			player = GUARDIANS.get(rand.nextInt(GUARDIANS.size()-1)).toString();
			playerBuild = generateBuild("magical").toString();
			break;
			
		case "Warrior":
			player = WARRIORS.get(rand.nextInt(WARRIORS.size()-1)).toString();
			build = generateBuild("physical");
			while (true) {
				int offensiveCount = 0;
				for (Item i : build) {
					if (!i.isMagical) {
						offensiveCount++;
					}
				}
				if (offensiveCount < 4) {
					build = generateBuild("physical");
				} else {
					break;
				}
			}
			playerBuild = build.toString();
			break;
			
		case "Assassin":
			player = ASSASSINS.get(rand.nextInt(ASSASSINS.size()-1)).toString();
			build = generateBuild("physical");
			while (true) {
				int offensiveCount = 0;
				for (Item i : build) {
					if (!i.isMagical) {
						offensiveCount++;
					}
				}
				if (offensiveCount < 4) {
					build = generateBuild("physical");
				} else {
					break;
				}
			}
			playerBuild = build.toString();
			break;
			
		case "Hunter":
			player = HUNTERS.get(rand.nextInt(HUNTERS.size()-1)).toString();
			build = generateBuild("physical");
			while (true) {
				int offensiveCount = 0;
				for (Item i : build) {
					if (!i.isMagical) {
						offensiveCount++;
					}
				}
				if (offensiveCount < 4) {
					build = generateBuild("physical");
				} else {
					break;
				}
			}
			playerBuild = build.toString();
			break;
		
		}

		ArrayList<String> loadout = new ArrayList<>();
		loadout.add(player);
		loadout.add(playerBuild);
		
		return loadout;
		
	}
	
	public ArrayList<Item> generateBuild(String type) {
        ArrayList<Item> build = new ArrayList<>();
        LinkedHashSet<Item> generation = new LinkedHashSet<>();
        Boolean matches;
        Item newItem = null;
        if (type.equals("physical")) {
            generation.add(getPhysicalBoot());
            for (int i = 0; i < 5; i++) {
                newItem = getPhysicalItem();
                generation.add(newItem);
            }

            while (generation.size() < 6) {
                newItem = getPhysicalItem();
                generation.add(newItem);
            }

            build.addAll(0, generation);
        } else {
            generation.add(getMagicalBoot());
            for (int i = 0; i < 5; i++) {
                newItem = getMagicalItem();
                generation.add(newItem);
            }

            while (generation.size() < 6) {
                newItem = getMagicalItem();
                generation.add(newItem);
            }
            build.addAll(0, generation);
        }
        return build;
    }
	
	private Item getPhysicalBoot() {
        Item boot;
        boot = BOOTS.get((rand.nextInt(BOOTS.size())));
        while (!boot.isPhysical) {
            boot = BOOTS.get((rand.nextInt(BOOTS.size())));
        }
        return boot;
    }

    private Item getMagicalBoot() {
        Item boot;
        boot = BOOTS.get((rand.nextInt(BOOTS.size())));
        while (!boot.isMagical) {
            boot = BOOTS.get((rand.nextInt(BOOTS.size())));
        }
        return boot;
    }

    private Item getPhysicalItem() {
        Item item;
        item = ITEMS.get((int)(Math.random() * (ITEMS.size() - 1) + 1));
        while (!item.isPhysical) {
            item = ITEMS.get((int)(Math.random() * (ITEMS.size() - 1) + 1));
        }
        return item;
    }

    private Item getMagicalItem() {
        Item item;
        item = ITEMS.get((int)(Math.random() * (ITEMS.size() - 1) + 1));
        while (!item.isMagical) {
            item = ITEMS.get((int)(Math.random() * (ITEMS.size() - 1) + 1));
        }
        return item;
    }
	
}
