
import java.util.*;
import java.lang.String;
import java.io.PrintWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
class Todo{

  public static void main(String[] args)throws FileNotFoundException
  {
    List<String> tdo =new ArrayList<String>();

    boolean quit=false;
    File myObj = new File("todo.txt");
     try{      myObj.createNewFile();
          }catch(Exception e){}

   Scanner sc =new Scanner(new File("todo.txt"));
            while(sc.hasNextLine()){
             tdo.add(sc.nextLine());
                         }
              sc.close();

        String info="Usage :-"+"\n"+ "$ ./todo add \"todo item\"  # Add a new todo"+"\n"+"$ ./todo ls               # Show remaining todos"+"\n"+"$ ./todo del NUMBER       # Delete a todo"+"\n"+"$ ./todo done NUMBER      # Complete a todo"+"\n"+"$ ./todo help             # Show usage"+"\n"+"$ ./todo report           # Statistics";


        String cmd;
             String cmd2;

       if(args.length ==0 || args[0].equals("help"))
         {System.out.print(info+"\n");}

else {
          do{
                cmd2= args[0];

             try{

                  if(args.length==1)
               {
                   switch(args[0]){
                 case "add" : System.out.print("Error: Missing todo string. Nothing added!"+"\n");quit=true;break;
                 case "done": System.out.print("Error: Missing NUMBER for marking todo as done."+"\n");quit=true;break;
                 case "del" : System.out.print("Error: Missing NUMBER for deleting todo."+"\n");quit=true;break;
                 default    : quit=true;break;
                                 }
               }

              else
                {  cmd=args[1];
                   quit= option(tdo,cmd2,cmd,info);


                 }}catch(Exception e){

                 }
               }while(quit==false);



   }
}




 public static void add(List<String> tdo, String item) throws FileNotFoundException
{
   try{File file =new File("todo.txt");
   FileWriter fw = new FileWriter(file,true);
   BufferedWriter bw = new BufferedWriter(fw);
PrintWriter writer= new PrintWriter(bw);
writer.println(item);
tdo.add(item);

writer.flush();
writer.close();
System.out.println("Added todo: "+"\""+item+"\"");
}catch(IOException ioe){
    	   System.out.println("Exception occurred:");
    	   ioe.printStackTrace();


}
}
public static boolean option(List<String> tdo,String cmd2,String cmd,String info)throws FileNotFoundException
{
      switch(cmd2)
      {
        case "add" : add(tdo,cmd);return true;
        case "del" : remo(tdo,cmd);return true;
        case "done" : done(tdo,cmd);return true;
        case "help" : System.out.printf(info); return true;
        default : return true;
      }
}
public static void remo(List<String> tdo, String item)
{  File newfile = new File("temp.txt");
   File oldfile = new File("todo.txt");

    String currentline;int d=Integer.valueOf(item)-1;
    if(d<0 ||d>tdo.size()-1)
  {
    System.out.print("Error: todo #"+item+" does not exist. Nothing deleted."+"\n");

  }
  else{      int c=d;

        String line= tdo.get(c);
      try{ FileWriter fw = new FileWriter(newfile);

           BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer= new PrintWriter(bw);
            FileReader fr = new FileReader(oldfile);
            BufferedReader br= new BufferedReader(fr);
            while((currentline = br.readLine()) != null)
            { if(!currentline.equals(line))
              {
               writer.write(currentline+"\n");
              }

            }
            writer.flush();
            writer.close();
            fr.close();
            br.close();
            fw.close();
            oldfile.delete();
            newfile.renameTo(oldfile);
            tdo.remove(d);
              c=c+1;
            System.out.print("Deleted todo #"+c+"\n");
    }catch(Exception e){

    }
}
}

public static void done(List<String> tdo, String item)
{
   File oldfile = new File("todo.txt");
   File newfile = new File("temp.txt");
    String currentline;int d=Integer.valueOf(item)-1;
    if(d<0 || d>tdo.size()-1)
  {
     System.out.print("Error: todo #"+item+" does not exist."+"\n");
  }
  else{      int c= d;

        String line= tdo.get(c);

        Date date= new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      try{ FileWriter fw = new FileWriter("done.txt",true);

           BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer= new PrintWriter(bw);


            FileReader fr = new FileReader(oldfile);
            BufferedReader br= new BufferedReader(fr);

           writer.write("x"+" "+df.format(date)+" "+line+"\n");


           FileWriter fw2 = new FileWriter(newfile,true);
           BufferedWriter bw2 = new BufferedWriter(fw2);
          PrintWriter writer2= new PrintWriter(bw2);
           while((currentline = br.readLine()) != null)
           { if(!currentline.equals(line))
             {
              writer2.write(currentline+"\n");
             }
           }
            writer2.flush();
            writer2.close();
            fw2.close();
            writer.flush();
            writer.close();
            fw.close();
            br.close();
            fr.close();
            oldfile.delete();
            newfile.renameTo(oldfile);

            tdo.remove(c);


            c=c+1;
            System.out.print("Marked todo #"+c+" as done."+"\n");
          }catch(Exception e){

          }
  }}
public static void comp(List<String> tdo, String item)
{}

public static void rep(List<String> tdo)
{}





}
