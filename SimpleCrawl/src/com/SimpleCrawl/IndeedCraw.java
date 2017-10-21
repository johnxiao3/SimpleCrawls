package com.SimpleCrawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndeedCraw {
	
	public String connectStr;
    public String urlStr="https://www.indeed.com/jobs?q=semiconductor&start=";

	public IndeedCraw() {
		// TODO Auto-generated constructor stub
	}
	
	public void test(){
		System.out.println("good");
	}
	
	public void connect()
	{
		URL url;
		StringBuilder sb = new StringBuilder(); 
		try {
			String str=""; 
			url = new URL(urlStr); 
			InputStreamReader input0=new InputStreamReader(url.openStream());
	        BufferedReader reader =new BufferedReader(input0);    
            String temp="";
            while((temp=reader.readLine())!=null){     
            	//System.out.println(temp);  
                sb.append(temp);
            }
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		connectStr = sb.toString();
	}

	public void titleMatch(){
		String regex="title=\"([^\"]*)\"class=\"turnstileLink\"";
		Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(connectStr);
        int count = 0;
        while(matcher.find()) {
            count++;
            //System.out.println("found: " + count + " : ");
            System.out.println(matcher.group(1));
        }
	}
	
	public void printTitle(int N){
		int i;
		urlStr="https://www.indeed.com/q-semiconductor-jobs.html";
		this.connect();
		this.titleMatch();
		for(i=1;i<N;i++)
		{
			urlStr="https://www.indeed.com/jobs?q=semiconductor&start="+Integer.toString(i*10);	
			this.connect();
			this.titleMatch();
		}
	}
}
