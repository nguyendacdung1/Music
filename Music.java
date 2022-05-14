package music;

import java.sql.*;
import java.util.Scanner;

public class Music {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try(
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/albummusic","root","");

                Statement stmt = conn.createStatement();
                ){
            String sqlSelect = "select*from music";
            System.out.println("The SQL stament: "+sqlSelect);
            ResultSet resultSet = stmt.executeQuery(sqlSelect);

            while (resultSet.next()){
                System.out.println("Album: "+ resultSet.getInt("id")+","
                        +resultSet.getString("name")+","
                        +resultSet.getString("author")+","
                        +resultSet.getInt("year")
                );
            }
            //update
            String sqlUpdate = "Update music Set year = 2013 where music.id = 1001";
            int countUpdate = stmt.executeUpdate(sqlUpdate);
            System.out.println("Record = "+countUpdate);

            //Thêm 1 music vào album
//            String sqlInsert = "insert into music value" + "(1005,'nang tho','Hoang Dung',2021)";
//            System.out.println("The SQL stament "+sqlInsert+"\n");
//            int countInsert = stmt.executeUpdate(sqlInsert);
//            System.out.println(countInsert +"Updated ");
            //Thêm 1 bản ghi cho người dùng nhập
            System.out.println("Insert a record");
            System.out.println("id: "); Integer insertID = sc.nextInt(); sc.nextLine();
            System.out.println("name: "); String insertName = sc.nextLine();
            System.out.println("author: "); String insertAuthor = sc.nextLine();
            System.out.println("year: "); Integer insertYear = sc.nextInt();
            String sqlInsert = "insert into music value (" +insertID+",'"
                    +insertName+"', '"
                    +insertAuthor+"', '"
                    +insertYear+"'.)";
            System.out.println("The SQL stament is: "+sqlInsert);
            int countInsert = stmt.executeUpdate(sqlInsert);
            System.out.println(countInsert+"record insert.\n"
            );

            //xóa 1 music cho phép người dùng nhập
            System.out.println("\nEnter the musicId you want to deletete: ");
            Integer deleteId = sc.nextInt();
            String sqlDelete = "delete from music where id = "+deleteId;
            System.out.println("The SQL stament is: "+sqlDelete +"\n");
            int countDelete = stmt.executeUpdate(sqlDelete);
            System.out.println(countDelete+"Record deleted.");

            //Search tên tac giả
            System.out.println("\nCac bai hat cua Vu.");
            String strSelect = "select * from music where author like '%Vu.%'";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()){
                System.out.println(rset.getInt("id")+","
                        + rset.getString("name")+","
                        +rset.getString("author")+","
                        +rset.getInt("year")+"."
                );
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
