package Project;

import Project.Piece;
import Project.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.io.*;

public class HelperCh {

	HashMap<Piece, ArrayList<Point>> moveshm;

	ArrayList<Piece> pieces;

	public void loadvals() {

	}

	private char translatey(int y) {
		char cord = 0;

		if (y == 0) {
			cord = 'A';
		}
		if (y == 1) {
			cord = 'B';
		}
		if (y == 2) {
			cord = 'C';
		}
		if (y == 3) {
			cord = 'D';
		}
		if (y == 4) {
			cord = 'E';
		}
		if (y == 5) {
			cord = 'F';
		}
		if (y == 6) {
			cord = 'G';
		}
		if (y == 7) {
			cord = 'H';
		}

		return cord;
	}

	public void moves() {

		for (int i = 0; i < pieces.size(); i++) {
			ArrayList<Point> ans = new ArrayList<Point>();
			Piece pieceinquestion = pieces.get(i);
			int x = pieces.get(i).getLocation().getx();
			int y = pieces.get(i).getLocation().gety();

			if (pieces.get(i).getType() == "rook") {

				for (int a = x; a < 8; a++) {
					boolean inputcheck = true;
					int count;
					for (count = 0; count < pieces.size(); count++) {
						if (pieces.get(count).getLocation().matches(a, y)) {
							inputcheck = false;
						}
					}

					if (inputcheck == true) {
						Point ptr = new Point(a, y, false);
						ans.add(ptr);
					} else if (inputcheck == false
							&& (pieces.get(count).getside().equals(pieceinquestion.getside()) == false)) {
						Point ptr = new Point(a, y, false);
						ans.add(ptr);
					} else {
						break;
					}

				}
				for (int b = x; b >= 0; b--) {
					boolean inputcheck = true;
					int count;
					for (count = 0; count < pieces.size(); count++) {
						if (pieces.get(count).getLocation().matches(b, y)) {
							inputcheck = false;
						}
					}

					if (inputcheck == true) {
						Point ptr = new Point(b, y, false);
						ans.add(ptr);
					} else if (inputcheck == false
							&& (pieces.get(count).getside().equals(pieceinquestion.getside()) == false)) {
						Point ptr = new Point(b, y, false);
						ans.add(ptr);
					} else {
						break;
					}
				}
				for (int c = y; c < 8; c++) {
					boolean inputcheck = true;
					int count;
					for (count = 0; count < pieces.size(); count++) {
						if (pieces.get(count).getLocation().matches(x, c)) {
							inputcheck = false;
						}
					}

					if (inputcheck == true) {
						Point ptr = new Point(x, c, false);
						ans.add(ptr);
					} else if (inputcheck == false
							&& (pieces.get(count).getside().equals(pieceinquestion.getside()) == false)) {
						Point ptr = new Point(x, c, false);
						ans.add(ptr);
					} else {
						break;
					}
				}
				for (int d = y; d >= 0; d--) {
					boolean inputcheck = true;
					int count;
					for (count = 0; count < pieces.size(); count++) {
						if (pieces.get(count).getLocation().matches(x, d)) {
							inputcheck = false;
						}
					}

					if (inputcheck == true) {
						Point ptr = new Point(x, d, false);
						ans.add(ptr);
					} else if (inputcheck == false
							&& (pieces.get(count).getside().equals(pieceinquestion.getside()) == false)) {
						Point ptr = new Point(x, d, false);
						ans.add(ptr);
					} else {
						break;
					}
				}

			}
			if (pieceinquestion.getType() == "bishop") {

				int a = x;
				int b = y;

				while ((a >= 0 && a < 8) && (b >= 0 && b < 8)) {
					boolean check = true;

					for (int j = 0; j < pieces.size(); j++) {
						if (pieces.get(j).getLocation().matches(a, b)) {
							if (pieceinquestion.getside().equals(pieces.get(j).getside()) == false) {
								Point ptr = new Point(a, b, false);
								ans.add(ptr);
							}
							check = false;
							break;

						}
					}

					if (check == false) {
						break;
					}

					Point ptr = new Point(a, b, false);
					ans.add(ptr);

					a++;
					b++;
				}

				while ((a >= 0 && a < 8) && (b >= 0 && b < 8)) {
					boolean check = true;

					for (int j = 0; j < pieces.size(); j++) {
						if (pieces.get(j).getLocation().matches(a, b)) {
							if (pieceinquestion.getside().equals(pieces.get(j).getside()) == false) {
								Point ptr = new Point(a, b, false);
								ans.add(ptr);
							}
							check = false;
							break;

						}
					}

					if (check == false) {
						break;
					}

					Point ptr = new Point(a, b, false);
					ans.add(ptr);

					a--;
					b--;
				}

				while ((a >= 0 && a < 8) && (b >= 0 && b < 8)) {
					boolean check = true;

					for (int j = 0; j < pieces.size(); j++) {
						if (pieces.get(j).getLocation().matches(a, b)) {
							if (pieceinquestion.getside().equals(pieces.get(j).getside()) == false) {
								Point ptr = new Point(a, b, false);
								ans.add(ptr);
							}
							check = false;
							break;

						}
					}

					if (check == false) {
						break;
					}

					Point ptr = new Point(a, b, false);
					ans.add(ptr);
					a++;
					b--;
				}

				while ((a >= 0 && a < 8) && (b >= 0 && b < 8)) {
					boolean check = true;

					for (int j = 0; j < pieces.size(); j++) {
						if (pieces.get(j).getLocation().matches(a, b)) {
							if (pieceinquestion.getside().equals(pieces.get(j).getside()) == false) {
								Point ptr = new Point(a, b, false);
								ans.add(ptr);
							}
							check = false;
							break;

						}
					}

					if (check == false) {
						break;
					}

					Point ptr = new Point(a, b, false);
					ans.add(ptr);
					a--;
					b++;
				}

			}
			if (pieceinquestion.getType() == "pawn") {
				if (pieceinquestion.getside() == "white") {
					if ((y + 1 >= 0 && y + 1 < 8)) {
						boolean check1 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x, y + 1)) {
								check1 = false;
								break;
							}
						}
						if (check1 == true) {
							Point ptr1 = new Point(x, y + 1, false);
							ans.add(ptr1);

						}
					}

					if ((x + 1 >= 0 && x + 1 < 8)) {
						if ((y + 1 >= 0 && y + 1 < 8)) {
							boolean check2 = true;
							for (int w = 0; w < pieces.size(); w++) {
								if (pieces.get(w).getLocation().matches(x + 1, y + 1)
										&& pieces.get(w).getside().equals(pieceinquestion.getside()) == false) {
									check2 = false;
									break;
								}
							}
							if (check2 == true) {
								Point ptr2 = new Point(x + 1, y + 1, true);
								ans.add(ptr2);
							}

						}
					}
					if ((x - 1 >= 0 && x - 1 < 8)) {
						if ((y + 1 >= 0 && y + 1 < 8)) {
							boolean check3 = true;
							for (int w = 0; w < pieces.size(); w++) {
								if (pieces.get(w).getLocation().matches(x - 1, y + 1)
										&& pieces.get(w).getside().equals(pieceinquestion.getside()) == false) {
									check3 = false;
									break;
								}
							}
							if (check3 == true) {
								Point ptr3 = new Point(x - 1, y + 1, true);
								ans.add(ptr3);
							}
						}

					}

				}

				else {

					if ((y - 1 >= 0 && y - 1 < 8)) {
						boolean check1 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x, y - 1)) {
								check1 = false;
								break;
							}
						}
						if (check1 == true) {
							Point ptr1 = new Point(x, y - 1, false);
							ans.add(ptr1);

						}
					}

					if ((x + 1 >= 0 && x + 1 < 8)) {
						if ((y - 1 >= 0 && y - 1 < 8)) {
							boolean check2 = true;
							for (int w = 0; w < pieces.size(); w++) {
								if (pieces.get(w).getLocation().matches(x + 1, y - 1)
										&& pieces.get(w).getside().equals(pieceinquestion.getside()) == false) {
									check2 = false;
									break;
								}
							}
							if (check2 == true) {
								Point ptr2 = new Point(x + 1, y - 1, true);
								ans.add(ptr2);
							}

						}
					}
					if ((x - 1 >= 0 && x - 1 < 8)) {
						if ((y - 1 >= 0 && y - 1 < 8)) {
							boolean check3 = true;
							for (int w = 0; w < pieces.size(); w++) {
								if (pieces.get(w).getLocation().matches(x - 1, y - 1)
										&& pieces.get(w).getside().equals(pieceinquestion.getside()) == false) {
									check3 = false;
									break;
								}
							}
							if (check3 == true) {
								Point ptr3 = new Point(x - 1, y - 1, true);
								ans.add(ptr3);
							}
						}

					}

				}

			}
			if (pieces.get(i).getType() == "knight") {
				if (y + 2 < 8 && y + 2 >= 0) {
					if (x + 1 < 8 && x + 1 >= 0) {
						boolean check4 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x + 1, y + 2)) {
								if (pieceinquestion.getside().equals(pieces.get(w).getside())) {
									check4 = false;
								}
								break;
							}
						}
						if (check4 == true) {
							Point ptr = new Point(x + 1, y + 2, false);
							ans.add(ptr);
						}

					}
					if (x - 1 < 8 && x - 1 >= 0) {
						boolean check4 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x - 1, y + 2)) {
								if (pieceinquestion.getside().equals(pieces.get(w).getside())) {
									check4 = false;
								}
								break;
							}
						}
						if (check4 == true) {
							Point ptr = new Point(x - 1, y + 2, false);
							ans.add(ptr);
						}

					}
				}
				if (y - 2 < 8 && y - 2 >= 0) {
					if (x + 1 < 8 && x + 1 >= 0) {
						boolean check4 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x + 1, y - 2)) {
								if (pieceinquestion.getside().equals(pieces.get(w).getside())) {
									check4 = false;
								}
								break;
							}
						}
						if (check4 == true) {
							Point ptr = new Point(x + 1, y - 2, false);
							ans.add(ptr);
						}
					}
					if (x - 1 < 8 && x - 1 >= 0) {
						boolean check4 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x - 1, y - 2)) {
								if (pieceinquestion.getside().equals(pieces.get(w).getside())) {
									check4 = false;
								}
								break;
							}
						}
						if (check4 == true) {
							Point ptr = new Point(x - 1, y - 2, false);
							ans.add(ptr);
						}
					}
				}
				if (x + 2 < 8 && x + 2 >= 0) {
					if (y + 1 < 8 && y + 1 >= 0) {
						boolean check4 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x + 2, y + 1)) {
								if (pieceinquestion.getside().equals(pieces.get(w).getside())) {
									check4 = false;
								}
								break;
							}
						}
						if (check4 == true) {
							Point ptr = new Point(x + 2, y + 1, false);
							ans.add(ptr);
						}
					}
					if (y - 1 < 8 && y - 1 >= 0) {
						boolean check4 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x + 2, y - 1)) {
								if (pieceinquestion.getside().equals(pieces.get(w).getside())) {
									check4 = false;
								}
								break;
							}
						}
						if (check4 == true) {
							Point ptr = new Point(x + 2, y - 1, false);
							ans.add(ptr);
						}
					}
				}
				if (x - 2 < 8 && x - 2 >= 0) {
					if (y + 1 < 8 && y + 1 >= 0) {
						boolean check4 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x - 2, y + 1)) {
								if (pieceinquestion.getside().equals(pieces.get(w).getside())) {
									check4 = false;
								}
								break;
							}
						}
						if (check4 == true) {
							Point ptr = new Point(x - 2, y + 1, false);
							ans.add(ptr);
						}
					}
					if (y - 1 < 8 && y - 1 >= 0) {
						boolean check4 = true;
						for (int w = 0; w < pieces.size(); w++) {
							if (pieces.get(w).getLocation().matches(x - 2, y - 1)) {
								if (pieceinquestion.getside().equals(pieces.get(w).getside())) {
									check4 = false;
								}
								break;
							}
						}
						if (check4 == true) {
							Point ptr = new Point(x - 2, y - 1, false);
							ans.add(ptr);
						}
					}
				}

			}

			if (pieces.get(i).getType() == "queen") {

			}
			if (pieceinquestion.getType() == "king") {
				ans = kingmoves(pieceinquestion);
			}

			moveshm.put(pieceinquestion, ans);

		}

	}

	public ArrayList<Message> piecesafety(Piece p) {
		ArrayList<Message> ans = new ArrayList<Message>();

		int x = p.getLocation().getx();
		int y = p.getLocation().gety();
		String side = p.getside();
		int value = p.getValue();
		ArrayList<Point> pmoves = moveshm.get(p);

		// go through HM for moves on p's location

		for (int i = 0; i < pmoves.size(); i++) {

			Point move = pmoves.get(i);
			int xm = move.getx();
			int ym = move.gety();

			Set<Piece> keys = moveshm.keySet();
			Iterator<Piece> iterator = keys.iterator();

			while (iterator.hasNext()) {
				Piece key = iterator.next();

				ArrayList<Point> movesother = moveshm.get(key);

				int count = 0;
				int msgvalue = 0;

				for (int j = 0; j < movesother.size(); j++)

				{

					count = currentcount(movesother.get(j));
					msgvalue = currentvalue(movesother.get(j));

					if (movesother.get(j).matches(xm, ym) && movesother.get(j).isAttack() == true) {
						// same side
						if (key.getside().equals(side)) {
							count++;
							msgvalue = msgvalue + key.getValue();
						}
						// opp side
						else {
							count--;
							msgvalue = msgvalue - key.getValue();
						}
					}

					String advice = "";

				}

			}

		}
		return ans;

	}

	private int currentvalue(Point point) {
		int x = point.getx();
		int y = point.gety();

		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getLocation().matches(x, y)) {
				return pieces.get(i).getValue();
			}
		}

		return 0;

	}

	private int currentcount(Point point) {
		int x = point.getx();
		int y = point.gety();

		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getLocation().matches(x, y)) {
				return 1;
			}
		}

		return 0;

	}

	private ArrayList<Point> kingmoves(Piece p2) {
		ArrayList<Point> ans = new ArrayList<Point>();

		int x = p2.getLocation().getx();
		int y = p2.getLocation().gety();

		if (y + 1 < 8 && y + 1 >= 0) {
			if (x + 1 < 8 && x + 1 >= 0) {
				boolean check = true;
				for (int w = 0; w < pieces.size(); w++) {
					if (pieces.get(w).getLocation().matches(x + 1, y + 1)) {
						if (pieces.get(w).getside().equals(p2.getside())) {
							check = false;
						}

					}
				}

				Set<Piece> keys = moveshm.keySet();
				Iterator<Piece> iterator = keys.iterator();

				while (iterator.hasNext()) {
					Piece key = iterator.next();

					ArrayList<Point> moves = moveshm.get(key);

					for (int z = 0; z < moves.size(); z++) {
						if (key.getside().equals(p2.getside()) == false && moves.get(z).isAttack()
								&& moves.get(z).matches(x + 1, y + 1)) {
							check = false;
						}
					}

				}

				if (check == true) {
					Point ptr = new Point(x + 1, y + 1, true);
					ans.add(ptr);
				}

			}
			if (x - 1 < 8 && x - 1 >= 0) {

				boolean check = true;
				for (int w = 0; w < pieces.size(); w++) {
					if (pieces.get(w).getLocation().matches(x - 1, y + 1)) {
						if (pieces.get(w).getside().equals(p2.getside())) {
							check = false;
						}

					}
				}

				Set<Piece> keys = moveshm.keySet();
				Iterator<Piece> iterator = keys.iterator();

				while (iterator.hasNext()) {
					Piece key = iterator.next();

					ArrayList<Point> moves = moveshm.get(key);

					for (int z = 0; z < moves.size(); z++) {
						if (key.getside().equals(p2.getside()) == false && moves.get(z).isAttack()
								&& moves.get(z).matches(x - 1, y + 1)) {
							check = false;
						}
					}

				}

				if (check == true) {
					Point ptr = new Point(x - 1, y + 1, true);
					ans.add(ptr);
				}

			}
		}
		if (y - 1 < 8 && y - 1 >= 0) {
			if (x + 1 < 8 && x + 1 >= 0) {

				boolean check = true;
				for (int w = 0; w < pieces.size(); w++) {
					if (pieces.get(w).getLocation().matches(x + 1, y - 1)) {
						if (pieces.get(w).getside().equals(p2.getside())) {
							check = false;
						}

					}
				}

				Set<Piece> keys = moveshm.keySet();
				Iterator<Piece> iterator = keys.iterator();

				while (iterator.hasNext()) {
					Piece key = iterator.next();

					ArrayList<Point> moves = moveshm.get(key);

					for (int z = 0; z < moves.size(); z++) {
						if (key.getside().equals(p2.getside()) == false && moves.get(z).isAttack()
								&& moves.get(z).matches(x + 1, y - 1)) {
							check = false;
						}
					}

				}

				if (check == true) {
					Point ptr = new Point(x + 1, y - 1, true);
					ans.add(ptr);
				}

			}
			if (x - 1 < 8 && x - 1 >= 0) {

				boolean check = true;
				for (int w = 0; w < pieces.size(); w++) {
					if (pieces.get(w).getLocation().matches(x - 1, y - 1)) {
						if (pieces.get(w).getside().equals(p2.getside())) {
							check = false;
						}

					}
				}

				Set<Piece> keys = moveshm.keySet();
				Iterator<Piece> iterator = keys.iterator();

				while (iterator.hasNext()) {
					Piece key = iterator.next();

					ArrayList<Point> moves = moveshm.get(key);

					for (int z = 0; z < moves.size(); z++) {
						if (key.getside().equals(p2.getside()) == false && moves.get(z).isAttack()
								&& moves.get(z).matches(x - 1, y - 1)) {
							check = false;
						}
					}

				}

				if (check == true) {
					Point ptr = new Point(x - 1, y - 1, true);
					ans.add(ptr);
				}

			}
		}
		if (x + 1 < 8 && x + 1 >= 0) {
			boolean check = true;
			for (int w = 0; w < pieces.size(); w++) {
				if (pieces.get(w).getLocation().matches(x + 1, y)) {
					if (pieces.get(w).getside().equals(p2.getside())) {
						check = false;
					}

				}
			}

			Set<Piece> keys = moveshm.keySet();
			Iterator<Piece> iterator = keys.iterator();

			while (iterator.hasNext()) {
				Piece key = iterator.next();

				ArrayList<Point> moves = moveshm.get(key);

				for (int z = 0; z < moves.size(); z++) {
					if (key.getside().equals(p2.getside()) == false && moves.get(z).isAttack()
							&& moves.get(z).matches(x + 1, y)) {
						check = false;
					}
				}

			}

			if (check == true) {
				Point ptr = new Point(x + 1, y, true);
				ans.add(ptr);
			}

		}
		if (x - 1 < 8 && x - 1 >= 0) {
			boolean check = true;
			for (int w = 0; w < pieces.size(); w++) {
				if (pieces.get(w).getLocation().matches(x - 1, y)) {
					if (pieces.get(w).getside().equals(p2.getside())) {
						check = false;
					}

				}
			}

			Set<Piece> keys = moveshm.keySet();
			Iterator<Piece> iterator = keys.iterator();

			while (iterator.hasNext()) {
				Piece key = iterator.next();

				ArrayList<Point> moves = moveshm.get(key);

				for (int z = 0; z < moves.size(); z++) {
					if (key.getside().equals(p2.getside()) == false && moves.get(z).isAttack()
							&& moves.get(z).matches(x - 1, y)) {
						check = false;
					}
				}

			}

			if (check == true) {
				Point ptr = new Point(x - 1, y, true);
				ans.add(ptr);
			}

		}

		return ans;
	}
	
	private boolean isPinned(Piece p, Point destination)
	{
		boolean returnvalue=false;
		
		int kingx;
		int kingy;
		
		Piece king=p;
		
		for (int i=0; i<pieces.size();i++)
		{
			if(pieces.get(i).getType()=="king" && pieces.get(i).getside().equals(p.getside()))
			{
				king=pieces.get(i);
				kingx=pieces.get(i).getLocation().getx();
				kingy=pieces.get(i).getLocation().gety();
				break;
			}
		}
		
		Piece replacement= new Piece(p.getType(),p.getside(),destination);
		
		pieces.remove(p);
		
		pieces.add(replacement);
		
		moves();
		
		if(isinCheck(king))
		{
			returnvalue=true;
		}
		
		
		pieces.remove(replacement);
		pieces.add(p);
		
		moves();
		
		
		return returnvalue;
	}
	
	private boolean isinCheck(Piece king)
	{
		boolean returnvalue=false;
		
		int kingx=king.getLocation().getx();
		int kingy=king.getLocation().gety();
		String kingside=king.getside();
		
		Set<Piece> keys = moveshm.keySet();
		Iterator<Piece> iterator = keys.iterator();

		while (iterator.hasNext()) {
			Piece key = iterator.next();

			ArrayList<Point> moves = moveshm.get(key);
			
			for(int i=0; i<moves.size();i++)
			{
			
			if(key.getside().equals(kingside)==false)
			{
				if(moves.get(i).matches(kingx,kingy) && moves.get(i).isAttack())
				{
					returnvalue=true;
					break;
				}
			}
			
			}
        
		}
		
		
		
		return returnvalue;
	}
	
	private boolean causesindirectCheck(Piece p2, Point destination)
	{
		boolean answer=false;
		

		int kingx;
		int kingy;
		
		Piece enemyking=p2;
		
		for (int i=0; i<pieces.size();i++)
		{
			if(pieces.get(i).getType()=="king" && pieces.get(i).getside().equals(p2.getside())==false)
			{
				enemyking=pieces.get(i);
				kingx=pieces.get(i).getLocation().getx();
				kingy=pieces.get(i).getLocation().gety();
				break;
			}
		}
		
		
		Piece replacement= new Piece(p2.getType(),p2.getside(),destination);
		
		pieces.remove(p2);
		
		pieces.add(replacement);
		
		moves();
		
		if(isinCheck(enemyking))
		{
			answer=true;
		}
		
		
		pieces.remove(replacement);
		pieces.add(p2);
		
		moves();
		
		
		return answer;
	}
	
	
	
	//queens, bishops, rooks only
	
	private boolean doubledup(Point point,Point firstpiece, String side)
	{
		int x=point.getx();
		int y=point.gety();
		
		int x2=firstpiece.getx();
		int y2=firstpiece.gety();
		
		if(x==x2 && y!=y2)
		{
			if(y2>y)
			{
				for (int i=y2;i<8;i++)
				{
					
				}
			}
			else
			{
				for (int i=y2;i>=0;i--)
				{
					
				}
			}
		}
		else if(x!=x2 && y==y2)
		{
			
		}
		else 
		{
			
		}
		
		
	}
	
	
	
	
	

}
