package lse;

import java.io.*;
import java.util.*;

/**
 * This class builds an index of keywords. Each keyword maps to a set of pages in
 * which it occurs, with frequency of occurrence in each page.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in 
	 * DESCENDING order of frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash set of all noise words.
	 */
	HashSet<String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashSet<String>(100,2.0f);
	}
	
	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeywordsFromDocument(String docFile) 
	throws FileNotFoundException {
		
		
		if (docFile.isEmpty()==true)
		{
			throw new FileNotFoundException("Not found");
		}
		
		HashMap<String,Occurrence> results= new HashMap<String,Occurrence>();
		
		Scanner sc = new Scanner(new File(docFile));
		
		while (sc.hasNext()==true)
		{
			
			String input=sc.next();
			String finalword=getKeyword(input);
			
			if (finalword!=null && (finalword.equals("")==false) && (finalword.substring(0,1).equals(" ")==false))
			{
			
				if (results.containsKey(finalword)==true)
				{
					results.get(finalword).frequency++;
				}
				else
				{
					Occurrence number= new Occurrence(docFile,1);
					results.put(finalword,number);
				}
			}
			
			
		}
		sc.close();
		
		return results;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeywords(HashMap<String,Occurrence> kws) {
		
		Set<String> keys = kws.keySet(); 
		Iterator<String> iterator = keys.iterator();
		
		while (iterator.hasNext())
		{
			String kwskey = iterator.next();
			
			Occurrence value=kws.get(kwskey);
			
			if (keywordsIndex.containsKey(kwskey)==true)
			{
				ArrayList<Occurrence> insert = keywordsIndex.get(kwskey);
				insert.add(value);
				insertLastOccurrence(insert);
				keywordsIndex.put(kwskey,insert);
				
				
			}
			else
			{
				ArrayList<Occurrence> insert2 = new ArrayList<Occurrence>();
				//at end
				insert2.add(value);
				keywordsIndex.put(kwskey,insert2);
				
				
				
			}
			
			
			
			
		}
		
		
		
		
		
		
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * trailing punctuation(s), consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * NO OTHER CHARACTER SHOULD COUNT AS PUNCTUATION
	 * 
	 * If a word has multiple trailing punctuation characters, they must all be stripped
	 * So "word!!" will become "word", and "word?!?!" will also become "word"
	 * 
	 * See assignment description for examples
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyword(String word) {
		/** COMPLETE THIS METHOD **/
		
		if (word==null)
		{
			return null;
		}
		if (word.equals(""))
		{
			return null;
		}
		if (word.substring(0,1).equals(" "))
		{
			return null;
		}
		
		String testingword=word.toLowerCase();
		boolean punconlyatend=true;
		boolean allletters=true;
		
		int count=0;
		
		for (int i=0; i<testingword.length();i++)
		{
			char c= testingword.charAt(i);
			if (Character.isLetter(c)==false)
			{
				count=i;
				allletters=false;
				if (c!='.'&& c!=','&& c!='?' && c!=':' && c!=';' && c!='!')
				{
					punconlyatend=false;
					
				}
			    break;	
			}
		}
		
		
		if (allletters==true)
		{
			if (noiseWords.contains(testingword))
			{
				return null;
			}
			else
			{
				return testingword;
			}
		}
		else
		{
			for (int x=count;x<testingword.length();x++)
			{
				char c2=testingword.charAt(x);
				
				
				if (Character.isLetter(c2)==true || (c2!='.'&& c2!=','&& c2!='?' && c2!=':' && c2!=';' && c2!='!') )
				{
					punconlyatend=false;
					break;
				}
				
			}
			if (punconlyatend==false)
			{
				return null;
			}
			else
			{
				String result=testingword.substring(0,count);
				if (noiseWords.contains(result))
				{
					return null;
				}
				else
				{
					return result;
				}
			}
			
		}
		
		
		
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion is done by
	 * first finding the correct spot using binary search, then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		/** COMPLETE THIS METHOD **/
		
		if (occs==null || occs.isEmpty()==true||occs.size()<=1)
		{
			return null;
		}
		
		ArrayList<Integer> mids= new ArrayList<Integer>();
		
		
		int low=0;
		int hi=occs.size()-2;
		int mid;
		int lastelement=occs.get(occs.size()-1).frequency;
		
		
		while(low<=hi)
		{
			mid=(low+hi)/2;
			
			mids.add(mid);
			
			if (lastelement==occs.get(mid).frequency)
			{
				
				break;
			}
			else if (lastelement<occs.get(mid).frequency)
			{
				low=mid+1;
			}
			else
			{
				hi=mid-1;
			}
			
			
		}
		
		int lastindex=mids.get(mids.size()-1);
		
		if (lastelement>occs.get(lastindex).frequency)
		{
			Occurrence insert=occs.get(occs.size()-1);
			occs.add(lastindex, insert);
			occs.remove(occs.size()-1);
		}
		else
		{
			Occurrence insert=occs.get(occs.size()-1);
			occs.add(lastindex+1, insert);
			occs.remove(occs.size()-1);
		}
		
		
		
		
		return mids;
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.add(word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeywordsFromDocument(docFile);
			mergeKeywords(kws);
		}
		sc.close();
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of document frequencies. 
	 * 
	 * Note that a matching document will only appear once in the result. 
	 * 
	 * Ties in frequency values are broken in favor of the first keyword. 
	 * That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2 also with the same 
	 * frequency f1, then doc1 will take precedence over doc2 in the result. 
	 * 
	 * The result set is limited to 5 entries. If there are no matches at all, result is null.
	 * 
	 * See assignment description for examples
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matches, 
	 *         returns null or empty array list.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		
		
		if (kw1==null&&kw2==null)
		{
			return null;
		}
		if (keywordsIndex.containsKey(kw1)==false && keywordsIndex.containsKey(kw2)==false)
		{
			ArrayList<String>temp= new ArrayList<String>();
			return temp;
		}
		
		
		ArrayList<String> answer= new ArrayList<String>();
		
		
		if (keywordsIndex.containsKey(kw1)==true && keywordsIndex.containsKey(kw2)==false)
		{
			
			ArrayList<Occurrence> kw1al= keywordsIndex.get(kw1);
			
			for (int j=0; (j<kw1al.size() && j<5);j++)
			{
				answer.add(kw1al.get(j).document);
			}
			
		  return answer;
		}
		else if(keywordsIndex.containsKey(kw1)==false && keywordsIndex.containsKey(kw2)==true)
		{
			
			ArrayList<Occurrence> kw2al= keywordsIndex.get(kw2);
			
			for (int j=0; (j<kw2al.size() && j<5);j++)
			{
				answer.add(kw2al.get(j).document);
			}
			
			return answer;
		}
		//both there
		else
		{
			
			ArrayList<Occurrence> occs1= keywordsIndex.get(kw1);
			ArrayList<Occurrence> occs2= keywordsIndex.get(kw2);
			
			
			int ptr=0;
			int ptr2=0;
			int length=0;
			
			while((ptr<occs1.size() || ptr2<occs2.size()) && length<5)
			{
				if ((ptr<occs1.size() && ptr2<occs2.size())    &&     (occs1.get(ptr).frequency>occs2.get(ptr2).frequency)  && (answer.contains(occs1.get(ptr).document)==false))
				{
					
					answer.add(occs1.get(ptr).document);
					length++;
					ptr++;
				}
				else if ((ptr<occs1.size() && ptr2<occs2.size())  &&  (occs2.get(ptr2).frequency>occs1.get(ptr).frequency)   && (answer.contains(occs2.get(ptr2).document)==false))
				{
					answer.add(occs2.get(ptr2).document);
					length++;
					ptr2++;
				}
				//== or outofbounds
				else
				{
					if (ptr<occs1.size() && ptr2<occs2.size())
					{
						if (occs1.get(ptr).frequency==occs2.get(ptr2).frequency)
						{
						
							if (answer.contains(occs1.get(ptr).document)==false)
							{
								answer.add(occs1.get(ptr).document);
								 
								ptr++;
								
								length++;
							}
							
							else {
								ptr++;
							}
							if (answer.contains(occs2.get(ptr2).document)==false)
							{
								if (length < 5)
								{
									
									
									answer.add(occs2.get(ptr2).document); 
									ptr2++;
									length++; 
									
									
								}
							}
							else {
								
								
								ptr2++;
								
								
							}
						}
						
						else if (occs1.get(ptr).frequency>occs2.get(ptr2).frequency)
						{
							
							
							ptr++;
						}
						else
						{
							
							ptr2++;
						}
						
						
						
				     }
					else if (ptr<occs1.size() && ptr2>=occs2.size())
					{
						if (answer.contains(occs1.get(ptr).document)==false)
						{
							answer.add(occs1.get(ptr).document);
							 
							ptr++;
							
							length++;
						}
						
						else {
							ptr++;
						}
					}
					else
					{
						if (answer.contains(occs2.get(ptr2).document)==false)
						{
							if (length < 5)
							{
								
								
								answer.add(occs2.get(ptr2).document); 
								ptr2++;
								length++; 
								
								
							}
						}
						else {
							
							
							ptr2++;
							
							
						}
					}
				}
				
				
			}
			
				
			
			return answer;
		}
		
		
		
		
	
	}
}
