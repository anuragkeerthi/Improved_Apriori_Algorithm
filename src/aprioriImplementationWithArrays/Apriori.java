
package aprioriImplementationWithArrays;
import com.sun.jmx.snmp.tasks.Task;
import javax.swing.*; 
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Apriori extends javax.swing.JFrame {
public int columns;
public int min_sup;
public int rangeOfItems;
public int totalTransactions;
public static String db;
long time;
String comboBoxChanged="",comboBoxValue="";
int textBoxValue=0,textBoxChanged=0;

Vector<Vector> data=new Vector<Vector>();
Vector colNames=new Vector();

 public static ArrayList<ArrayList<Integer>> array=new ArrayList<ArrayList<Integer>>();
 public static ArrayList<ArrayList<Integer>> l2=new ArrayList<ArrayList<Integer>>();

    public Apriori() {
        initComponents();
        setLocationRelativeTo(null);
        //jLabel3.setVisible(false);
        jLabel4.setText("9 transactions");
        //jProgressBar1.setVisible(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTimeForExecution = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Apriori Algorithm");
        setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Select the database");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Enter the minimum support");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Transaction1", "Transaction2", "Transaction3" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setText("3");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Perform Apriori Algorithm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTimeForExecution.setText("\n");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(" ");

        jLabel4.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTimeForExecution))))
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox1, 0, 134, Short.MAX_VALUE)
                        .addComponent(jTextField1)))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jTimeForExecution)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    void textCheck()
    {
        try
        {
        textBoxValue=Integer.parseInt(jTextField1.getText());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"The entered value is not an integer assuming minimum support as 3");
            jTextField1.setText("3");
            textCheck();
        }
        return;
    }
           
    DisplayTables display=null;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          //  jTimeForExecution.setVisible(false);
        try
        {
        textBoxValue=Integer.parseInt(jTextField1.getText());
        }
        catch(Exception e)
        {
            textCheck();
        }
        comboBoxValue=(String)jComboBox1.getSelectedItem();         
        if(textBoxValue==textBoxChanged&&comboBoxValue==comboBoxChanged)
        {                
                display.setVisible(true);
                return;
        }
        else//(textBoxValue!=textBoxChanged&&comboBoxValue!=comboBoxChanged)
        {            
            textBoxChanged=textBoxValue;
            comboBoxChanged=comboBoxValue;
            final int c[]=new int[500];
            int noOfTablesGenerated=0;       
            db = (String) jComboBox1.getSelectedItem();
            MyDataBase.createConnection(db);           
           switch(db)
           {
               case "Transaction1":  columns=4;
                                rangeOfItems=5;
                                totalTransactions=9;
                                try
                                {
                               min_sup=Integer.parseInt(jTextField1.getText());
                                }
                                catch(Exception e){
                                    JOptionPane.showMessageDialog(null,"Please enter an integer value");
                                }
                               if(min_sup>4)
                               {
                               JOptionPane.showMessageDialog(null,"Please enter the minimum support less than "+columns);
                               return;
                               }
                               break;
                              
               case "Transaction2":rangeOfItems=500;
                              columns=10;              
                             totalTransactions=100;
                             min_sup=Integer.parseInt(jTextField1.getText());                           
                             if(min_sup>10)
                             {    
                                       JOptionPane.showMessageDialog(null,"Please enter the minimum support less than "+columns);
                                       return;   
                             }                             
                              break; 
               case "Transaction3":columns=10;
                                   rangeOfItems=500;
                                   totalTransactions=1000;
                                   min_sup=Integer.parseInt(jTextField1.getText());
                                   if(min_sup>10)
                                   {
                                           JOptionPane.showMessageDialog(null,"Please enter the minimum support less than "+columns);
                                           return;
                                   }                                   
                                   break;            
           }

           long start=System.currentTimeMillis();
                   try
                   {	  
                           try
                           {
                               String drp1="drop table l1";
                               MyDataBase.executeUpdate(drp1); 
                           }catch(Exception e){}                             
                   String q="create table l1(item1 number,count number)";
                   MyDataBase.executeUpdate(q);
                   //int arr[]=new int[rangeOfItems+1];
                   try
                   {    
                   String q1="select * from Transactions";    
                   PreparedStatement stm1=MyDataBase.createStatement(q1);
                   ResultSet r1=stm1.executeQuery();
                   ResultSetMetaData rsmd1=r1.getMetaData();
                   int i1,j1=0;
                   int cols1=rsmd1.getColumnCount();
                   while(r1.next())
                   {
                           array.add(new ArrayList<Integer>());
                           r1.getInt(1);
                           for(i1=2;i1<=cols1;i1++)
                            array.get(j1).add(r1.getInt(i1));	
                           j1++;
                   }
                   r1.close();
                   stm1.close();
                   }
                   catch(Exception e)
                   {
                           System.out.println("Exception from retrieving Transactions : "+e);
                   }
                   
                      
                            int inc=4,p=1;
                   for(int k=0;k<array.size();k++)
                   {   
                      for(int l=0;l<array.get(k).size();l++)
                      {                     
                          c[array.get(k).get(l)]++;                
                      }    

                   } 
                     
                   try
                   {
                           String q3="insert into l1 values(?,?)";
                           PreparedStatement stmt1=MyDataBase.createStatement(q3);
                           for(int i=1;i<c.length;i++)
                           {    
                           if(c[i]>=min_sup)
                           {
                           stmt1.setInt(1, i);
                           stmt1.setInt(2,c[i]);
                           stmt1.executeUpdate();   
                           }
                           }
                           stmt1.close();
                           //setMyProgressBar(100/progressBarMax);System.out.println("Progress bar updated");
                   }
                   catch(Exception e){
                       JOptionPane.showMessageDialog(null,"Exception while inserting array values into table"+e);
                   }                   
                   //To generate the frequent itemsets
                   int stop=1;
                   for(int i=2;i<=10;i++)  
                   {
                       if(i==10)
                       {
                           display(i-1);
                            break;
                       }                       
                       stop=candidateGeneration(i);
                       if(stop==1)
                       {    
                           noOfTablesGenerated=i-1;
                           display(i-1);
                           break;
                       }
                   }
                   }
                   catch(Exception e)
                   {}                                
                  
                   MyDataBase.close();//closing connection
                   //Calculating time taken for execution
                   long end=System.currentTimeMillis();
                   time = end-start;
                   String st=""+time+"";
                   jTimeForExecution.setText("Time for execution = "+(end-start)+" ms ("+(float)(end-start)/1000+" seconds)");
                   display=new DisplayTables();
                   display.setValues(totalTransactions,time,data,colNames,noOfTablesGenerated,db,"AprioriAlgorithm");
                   display.setVisible(true);                    
        }        
    }
    
        public  int candidateGeneration(int p)
	{	
            //JOptionPane.showMessageDialog(null,"From cndidateGeneration "+p);
             int stop=1;
		try
		{
		int count1;
                try
                {
                        String drp1="drop table l"+p;
                        MyDataBase.executeUpdate(drp1);
                }
                catch(Exception e){
                }
                String enter="";
                for(int i=1;i<=p;i++)
                {
                    enter+="item"+i+" number, ";
                }    
               try
               {
                String q1="create table l"+p+"("+enter+"count number)";
                int sp=MyDataBase.executeUpdate(q1);
               }
               catch(Exception e)
               {
                   JOptionPane.showMessageDialog(null,"Exception!!!!!!!!!!!!!!!!!!!1 "+e);
               }
                
                String select="";
                int p1,p2;
                p1=p-1;
                p2=p-2;
                for(int j=1;j<=p1;j++)
                {
                        select+=" t1.item"+j+" , ";
                }    
                select+="t2.item"+p1;
                String condition="";
                for(int k=1;k<=p2;k++)
                {
                         condition+=" t1.item"+k+"=t2.item"+k+" and ";
                }    
                condition+="t1.item"+p1+"<t2.item"+p1;   
                String q1="select "+select+" from l"+p1+" as t1 inner join l"+p1+" as t2 on "+condition;
                // JOptionPane.showMessageDialog(null,"query:"+q1);
                 
                PreparedStatement stmt2=MyDataBase.createStatement(q1);
                 //JOptionPane.showMessageDialog(null,"after........ condition");
                ResultSet rs1=stmt2.executeQuery();
		int a[]=new int[p];
                String insert,s="";
                //JOptionPane.showMessageDialog(null,"after condition");
                for(int i=1;i<=p;i++)
                {
                    s+="?,";
                }    
                s+="?";
                 String q4="insert into l"+p+" values("+s+")";
                 PreparedStatement pstmt2=MyDataBase.createStatement(q4);                
                 int i,j,m=0;
		while(rs1.next())
		{
                    insert="";
                        for(i=1;i<=p;i++)
                        {    
			a[i-1]=rs1.getInt(i);
                        }
                         boolean flag=false;
                        if(p==2)
                        {                        
			count1=getCount(a);                                    
                        if(count1>=min_sup)
                        {     
                             l2.add(new ArrayList<Integer>());
                             for(j=0;j<a.length;j++)
                             {    
                                 pstmt2.setInt(j+1,a[j]);
                                 l2.get(m).add(a[j]); 
                             }
                             pstmt2.setInt(j+1,count1);                             
                             pstmt2.executeUpdate();
                             l2.get(m).add(count1);
                             m++;
                             stop=0;
                        }    
                        }
                        else
                        {    
                            try
                            {                                
                                for(int k=0;k<l2.size();k++)
                                {                                                               
                                       if(l2.get(k).get(0)==a[p-2])
                                       {                                
                                          if(l2.get(k).get(1)==a[p-1])
                                          {    
                                           flag=true;    
                                            break;   
                                          }
                                        }                                                             
                                }
                        /*String q5="select * from l2";
                        PreparedStatement pstmt3=MyDataBase.createStatement(q5);
                        ResultSet rs2=pstmt3.executeQuery();
                        while(rs2.next())
                        {
                            v=rs2.getInt(1);
                            if(v==a[p-2])
                            {
                                
                                if(rs2.getInt(2)==a[p-1])
                                {    
                                //JOptionPane.showMessageDialog(null,"value of l2 ....."+v+"..."+p+"....."+a[p-2]+"..."+a[p-1]);
                                flag=true;    
                                break;   
                                }
                            }    
                        }
                        rs2.close();
                        pstmt3.close();*/
                        if(flag==true)
                        {    
                        
			count1=getCount(a);
                                   
                         //JOptionPane.showMessageDialog(null,"From insert inton c2 ");
                        if(count1>=min_sup)
                        {   
                            for(i=0;i<a.length;i++)
                             {    
                                pstmt2.setInt(i+1,a[i]);
                             } 
                             pstmt2.setInt(i+1,count1);  
                             pstmt2.executeUpdate();
                             stop=0;
                        }    
                        }
                        }//
                         catch(Exception e)
                         {
                                JOptionPane.showMessageDialog(null,"Here is the exception"+p+e);
                         }    
                        }
                        }
                rs1.close();
                stmt2.close();                                                                          
            } catch(Exception e)
                {JOptionPane.showMessageDialog(null,"Exception fter condiotin "+e);}
                
                return stop;
	}
	public int getCount(int a[])
	{
		int count=0;
		try
		{
                    int available=0;                   
                    int flag,j;
                    for(int i=0;i<array.size();i++)
                    {
                        available=0;
                        for(int k=0;k<a.length;k++)
                        {    
                            for(j=0;j<array.get(i).size();j++)
                            {
                                if(a[k]==array.get(i).get(j))
                                 {
                                    available++;
                                    break;
                                 }    
                            }
                             if(available!=k+1)
                              break;
                        }
                         if(available==a.length)        
                              count++;	
                    }
                   
		}
		catch(Exception e)
                {}
		return count;
	}
        //Displaying final frequent itemsets generated
    @SuppressWarnings("empty-statement")
        public void display(int p)
        {
            try
            {
                int p1=p+1;
                PreparedStatement stmt1=MyDataBase.createStatement("select count(*) from l"+p);
                ResultSet rs1=stmt1.executeQuery();
                rs1.next();
                int total=rs1.getInt(1);
                rs1.close();
                stmt1.close();
                
                 String query="select * from l"+p; 
                PreparedStatement stmt=MyDataBase.createStatement(query);
                ResultSet rs=stmt.executeQuery();
             
                ResultSetMetaData rsmd=rs.getMetaData();
                int cols=rsmd.getColumnCount();
                //JOptionPane.showMessageDialog(null,"Total = "+total);
                int i,k,e;
                //System.out.println(" Final Frequent itemsets generated are ");                
                //String ele[][] = new String[total][cols];
                //String col[]= new String[p+1];
                data.clear();
                colNames.clear();
                for(int l=0;l<p;l++)
                {
                    e=l+1;
                    //col[l]="Item"+e;
                    colNames.add("Item"+e);
                }  
                //col[p]="Count";
                colNames.add("Count");
                k=0;
                while(rs.next())
                {       
                    data.add(new Vector());
                    for(int j=1;j<=p+1;j++)
                    {
                        i=rs.getInt(j);
                        //ele[k][j-1]=""+i;
                        data.get(k).add(""+i);
                    }
                    k++;
                }  
                
                /*JTable tb = new JTable(ele,col);
                JScrollPane jsp = new JScrollPane(tb);
                JLabel lb = new JLabel("Final frequent itemsets are : ");
                jp.add(lb);
                jp.add(jsp);
                jf.setBounds(0,0,600,600);
                jf.setLocationRelativeTo(null);*/
                l2.clear();
                array.clear();
                rs.close();
                stmt.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Exception while displaying"+e);
            }
                
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        if(jComboBox1.getSelectedItem()=="Transaction1")
            jLabel4.setText("9 transactions");
        else if(jComboBox1.getSelectedItem()=="Transaction2")
            jLabel4.setText("100 transactions");
        else if(jComboBox1.getSelectedItem()=="Transaction3")
            jLabel4.setText("1000 transactions");
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apriori().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jTimeForExecution;
    // End of variables declaration//GEN-END:variables
}
 
