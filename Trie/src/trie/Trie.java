package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {
	
	// prevent instantiation
	private Trie() { }
	
	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	public static TrieNode buildTrie(String[] allWords) {
	
		TrieNode root= new TrieNode(null,null,null);
		
		if (allWords.length==0)
		{
			return root;
		}
		
		Indexes word1index=new Indexes(0,(short)0, (short)(allWords[0].length()-1));
		
		root.firstChild=new TrieNode(word1index,null,null);
		
		if (allWords.length==1)
		{
			return root;
		}
		
		TrieNode current=root.firstChild;
		TrieNode prev=root.firstChild;
		boolean match=false;
		boolean firstlvl=true;
		
		for (int i=1;i<allWords.length;i++)
		{
			String word=allWords[i];
			int endcount=0;
			
			while (current!=null)
			{
				
				String triecheck=(allWords[current.substr.wordIndex]).substring(current.substr.startIndex, current.substr.endIndex+1);
				String c="";
				c=c+triecheck.charAt(0);
				if (word.startsWith(c)==true || (word.substring(current.substr.startIndex)).startsWith(c)==true)
				{
					match=true;
					firstlvl=false;
				}
				
				if (match==true)
				{
					 endcount=-1;
					 String word2=word.substring(current.substr.startIndex);
					
					 if (current.substr.startIndex!=0)
					 {
						for (int x=0;x<triecheck.length()&& x<word2.length();x++)
						{
							if (triecheck.charAt(x)!=word2.charAt(x))
							{
								break;
							}
							else
							{
								endcount=endcount+1;
							}
						}
						endcount=endcount+current.substr.startIndex;
					 }
					 else
					 {
						 for (int x=0;x<triecheck.length()&& x<word.length();x++)
							{
								if (triecheck.charAt(x)!=word.charAt(x))
								{
									break;
								}
								else
								{
									endcount=endcount+1;
								}
							}
					 }
					if (endcount==triecheck.length()-1 || endcount>triecheck.length())
					{
						prev=current;
						current=current.firstChild;
						match=false;
						continue;
					}
					else
					{
						prev=current;
						
						break;
					}
					
					
				}
				else
				{
					prev=current;
					current=current.sibling;
				}
				
					
			}
			
			
			
			if (current==null)
			{
				if (firstlvl==true)
				{
				Indexes addindex= new Indexes(i,(short)0,(short)(word.length()-1));
				TrieNode add=new TrieNode(addindex,null,null);
				prev.sibling=add;
				}
				else
				{
					Indexes addindex= new Indexes(i,prev.substr.startIndex,(short)(word.length()-1));
					TrieNode add=new TrieNode(addindex,null,null);
					prev.sibling=add;
				}
			}
			
			else
			{
				TrieNode existingsubtree=prev.firstChild;
				int currentwordindex= prev.substr.wordIndex;
				short currentstartindex=prev.substr.startIndex;
				short currentendindex=prev.substr.endIndex;
				
				Indexes newwordindex= new Indexes(i,(short) (endcount+1),(short)(word.length()-1));
				TrieNode addnew= new TrieNode(newwordindex,null,null);
				
				short prevendindex=(short)(endcount);
				prev.substr.endIndex=prevendindex;
				
				
				Indexes middlewordindex= new Indexes(currentwordindex,(short) (prevendindex+1),currentendindex);
				TrieNode middle= new TrieNode(middlewordindex,existingsubtree,null);
				prev.firstChild=middle;
				
				middle.sibling=addnew;
				
			}
			
			
			
			
			
			
			
			 current=root.firstChild;
			 prev=root.firstChild;
			 match=false;
			 firstlvl=true;
			
		}
		
		
		
		
		
		
		return root;
	}
	
	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public static ArrayList<TrieNode> completionList(TrieNode root,
										String[] allWords, String prefix) {
		
		
		
		ArrayList<TrieNode> results= new ArrayList<TrieNode>();
	    TrieNode root2=root.firstChild;
	    
		
		return completionList2(root2,allWords,prefix,results);
	}
	
	private static ArrayList<TrieNode> completionList2(TrieNode root,
			String[] allWords, String prefix, ArrayList<TrieNode> result)
	{
		if (root==null)
		{
			return null;
		}
		
		TrieNode ptr=root;
		String triecheck=(allWords[ptr.substr.wordIndex]).substring(ptr.substr.startIndex, ptr.substr.endIndex+1);
		//System.out.println("triechek="+triecheck);
		
		
		
		//fm
		if ((triecheck.equals(prefix)) || (prefix.length()==1 && triecheck.startsWith(prefix)) || (prefix.length()<triecheck.length() && triecheck.startsWith(prefix) ))
		{
			if (root.firstChild!=null)
			{
			getallleaves(root.firstChild,result);
			}
			else
			{
				result.add(root);
			}
			
		}
		else
		{
		
				if (root.firstChild!=null)//not leaf
				{
					if(prefix.startsWith(triecheck) || triecheck.startsWith(prefix))
					{
						int diff=ptr.substr.endIndex-ptr.substr.startIndex;
						int num=diff+1;
						
						ptr=ptr.firstChild;
						
						completionList2(ptr,allWords,prefix.substring(num),result);
					}
					else//no match, go to sibling
					{
						ptr=ptr.sibling;
						completionList2(ptr,allWords,prefix,result);
						
					}
				}
				else//leaf
				{
					if (triecheck.startsWith(prefix))
					{
						result.add(ptr);
					}
					else //no match prefix, then go to next sibling
					{
						ptr=ptr.sibling;
						completionList2(ptr,allWords,prefix,result);
					}
						
				}
				
		}
		if (result.size()==0)
		{
			return null;
		}
				
				
		
		return result;
	}
	
	private static void getallleaves(TrieNode root,
			ArrayList<TrieNode> result)
	{
		if (root==null)
		{
			return;
		}
		if (root.firstChild==null)
		{
			result.add(root);
			getallleaves(root.sibling,result);
		}
		else
		{
			getallleaves(root.firstChild,result);
			getallleaves(root.sibling,result);
		}
		
		
		
		
		
	}
	private static boolean isAVL (TrieNode root)
	{
		if (root==null)
		{
			return false;
		}
		return false;
	}


	
	
	
	
	
	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	
	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
							.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
