package com.example.myapplication;



import java.sql.*;


public class DataBaseSQL  {

    private static Connection conn = null;
    private static final String driver= "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url ="jdbc:mysql://localhost:3306/reward.me";

    public DataBaseSQL() {
       conn=null;
        try{
            System.out.println("1");
            Class.forName(driver);
            System.out.println("2");
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("3");
            if(conn!=null){
                System.out.println("Conectado a la base de datos");
            }
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
            System.out.println("Error conexion");
        }

    }
    public boolean Validar (String user, String pass) throws SQLException {
        System.out.println("Entro en metodo");
        String sql = "SELECT * FROM usuarios WHERE email='"+user+"' AND psw = '"+pass+"'";
        Statement st = null;
        boolean userFound = false;
        try{

            st= conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                System.out.println("Cambio Boolena ");
                userFound = true;
            }

            rs.close();
        }finally{
            if(st != null){
                st.close();
            }
        }

        return userFound;

    }



}
