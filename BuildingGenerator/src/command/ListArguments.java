package command;

import java.util.ArrayList;
import java.util.stream.Collectors;

public interface ListArguments {
	
	ArrayList<Argument> listArg = new ArrayList<Argument>();
	
	public default void add(int pos,String value) {
		listArg.add(new Argument(pos, value));
		
	}
	
	
	public default boolean isCorect(int pos,String name,String[] args) {
		
		
		if(args.length<=pos) {
			return false;
		}
		
		return args[pos].equalsIgnoreCase(name);
		
	}
	
	
	public static boolean isCorect(int pos,String name,String[] args,ArrayList<Argument> listArg) {
		if(args.length<=pos) {
			return false;
		}
		
		for(Argument a : listArg ) {
			
			if(a.getPosition()==pos&&a.getValue().equalsIgnoreCase(name)&&args[pos].equalsIgnoreCase(name)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public default  ArrayList<Argument> getAllForIndex(int i){
		return (ArrayList<Argument>) listArg.stream().filter(a->{return a.position==i;}).collect(Collectors.toList());
	}
	
	

	public static ArrayList<String> getAllForIndex(int i,ArrayList<Argument> list) {
		// TODO Auto-generated method stub
		ArrayList<String> listString = new ArrayList<String>();
		
		for(Argument a : list) {
			if(a.getPosition()==i) {
				listString.add(a.getValue());
			}
		}
		return listString;
	}
	

}
