package com.aishwarya.barrenLand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BarrenLandAnalysis {
	
	//declaration of variables
	//defines width and length of the field
	final int width = 400; 
	final int length = 600;
	//array which is used to represent each cell of the field
	int field[][];
	//queue used to stor the nodes
	Queue<Integer[]> qList; 
	//used to increment number of fertile lands
	static int fertileLandCount = 0;
	//to store the fertile land areas
	int areas[];
	//to check if the empty data
	boolean emptyData=false;
	
	//constructor used to initialize the variables 
	public BarrenLandAnalysis(){
		qList = new LinkedList<Integer []>();
		field = new int[width][length];	
		areas = new int[10]; // it can be increased as per the requirement
	}
	
	//this method is used to read the data from StdIN
	public void readInputFromStdIN(){
		try{
			//reads the data from StdIn 
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String data = br.readLine();
			//read data is passed on to shade the barren lands
			shadeBarrenLands(data);
			}
			catch(IOException e){
				e.printStackTrace();
		}
	}
 
	//this method is used to parse the data and shade the barren lands
	public void shadeBarrenLands(String data){	
			
		if(!data.isEmpty()){
			//parsing the data by splitting by ',' and removing quotes and braces
			String[] barrenLands = data.split(",");
			for(String land:barrenLands){
				land = land.replace("\"", "");
				land = land.replaceAll("[{}]", "");
				 if(!land.isEmpty()){
					 String[] barrenCoord = land.trim().split(" ");
					 //shading the barren land as 1 so that its distinguishable
					 for(int i = Integer.parseInt(barrenCoord[0]); i <= Integer.parseInt(barrenCoord[2]); i++)
						 for(int j = Integer.parseInt(barrenCoord[1]); j <= Integer.parseInt(barrenCoord[3]); j++)
							 field[i][j] = 1;
				 }
				 else 
					 emptyData=true;//to for check empty data
			}
		}
		 else 
			 emptyData=true;//to for check empty data
	}
	
 
	// method used to check if the node is eligible for consideration
	public void checkNodeEligibility(int x, int y){
		if(field[x][y] == 0){	
			//if eligible add it to the the queue
			Integer node[] = {x, y};
			qList.add(node);
		}
	}
	
	//checking border nodes of a node to be considered
	public void checkBorderingNodes(int x, int y){
		if(x > 0){
			//check right side node
			checkNodeEligibility(x-1, y);
		}
		if(x < (width - 1)){
			//check left side node
			checkNodeEligibility(x+1, y);
		}
		if(y > 0){
			//check above side node
			checkNodeEligibility(x, y-1);
		}
		if(y < (length - 1)){
			//check below side node
			checkNodeEligibility(x, y+1);
		}
	}
	
	//method for calculating the areas
	public String calcFertileAreas(){
		int x = 0;
		int y = 0;
		String area="";
		//if the empty data is true then simply output area as w*l
		if(emptyData){
			return area=Integer.toString(width*length);
		}
		else{
			//to go through the whole field
			while(x < width && y < length){
				//if there is no node added in the queue
				if(qList.isEmpty()) {
					//check if current node is not visited or barren
					if(field[x][y] == 0){		  
						//increment the fertile land count
						fertileLandCount++;
						//add node coordinates to queue
						Integer node[] = {x, y};
						qList.add(node);
					}
					//if y is reached full length, reset y and increment x
					if(y == (length-1)){
						y = 0;
						x++;
					}
					else //else increment y only
						y++;
				}
				//if queue is filled, inspect queue elements
				if(!qList.isEmpty()) {
					//remove the first element in the queue
					Integer node[] = qList.remove();
					//get the x and y coordinates of current node
					int a = node[0];
					int b = node[1];
					//check if the node is not visited or barren
					if(field[a][b] == 0){
						//mark ad visited
						field[a][b] = -1;
						//increment the area by 1 for this fertilelandcount which is area of current node
						areas[fertileLandCount]=areas[fertileLandCount]+1;
						//check and add bordering nodes for consideration
						checkBorderingNodes(a, b);
					}
				}
			}
		}
		
		//once the areas are computed of each fertile land, to be sorted and converted into string
		Arrays.sort(areas);
		for(int k=0;k<areas.length;k++){
			if(areas[k]!=0){
				//only the areas with more than 0 size should be considered
				area+=areas[k]+" ";
			}
		}
		//returning the area removing any while spaces if present
		return area.trim();
	}
	
	//
	public static void main(String []args){
		//main method which is used to test the code
		System.out.println("Enter Coordinates for Barren Lands:");
		BarrenLandAnalysis br = new BarrenLandAnalysis();
		//calling method for std in
		br.readInputFromStdIN();
		//displays the areas in sorted order
		System.out.println(br.calcFertileAreas());
	}
}

