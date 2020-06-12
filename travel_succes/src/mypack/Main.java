package mypack;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.Random;
public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner sc= new Scanner(System.in);
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        String cid = null;
        String aid = null;
        int flag2 = 0;
        while (flag2 == 0) {
            System.out.println("******************************WELCOME TO THE TRAVEL BOOKING PORTAL******************************");
            System.out.println("1.Create account");
            System.out.println("2.Login");
            int ans = sc.nextInt();
            switch (ans) {
                case 1:
                    Customer c = new Customer();
                    c.registerCustomer();
                    break;
                case 2:
                    int flag1 = 0;
                    while (flag1 == 0) {
                        System.out.println("1. Admin login");
                        System.out.println("2. Customer login");
                        int ans1 = sc.nextInt();
                        switch (ans1) {
                            case 1:
                                int flag = 0;
                                while (flag == 0) {
                                    sc.nextLine();
                                    System.out.println("Enter username(email id)");
                                    String us = sc.nextLine();
                                    System.out.println("Enter password");
                                    String ps = sc.nextLine();
                                    try {
                                        cfg = new Configuration();
                                        cfg.configure("hibernate.cfg.xml");
                                        factory = cfg.buildSessionFactory();
                                        session = factory.openSession();
                                        tx=null;
                                        String sql = "select AID, PASS from admin where EMAIL=:em";
                                        SQLQuery query = session.createSQLQuery(sql);
                                        query.setParameter("em", us);
                                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                                        List data = query.list();
                                        String p = null;
                                        if (data.isEmpty()) {
                                            System.out.println("Wrong username. Do you want to enter the \n 1.customer login \n 2.try again?");
                                            int ans3 = sc.nextInt();
                                            if (ans3 == 1) {
                                                flag = 1;
                                                break;
                                            }
                                            if (ans3 == 2) {
                                                continue;
                                            }
                                        } else {
                                            for (Object object : data) {
                                                Map row = (Map) object;
                                                aid = (String) row.get("AID");
                                                p = (String) row.get("PASS");
                                            }
                                            if (p.equals(ps)) {
                                                System.out.println("What operation do you want to perform? \n1.Add travel details. \n2.View travel details. \n3.Modify or delete travel details. \n 4.Back.");
                                                admin ad = new admin();
                                                int ans5 = sc.nextInt();
                                                if (ans5 == 1) {
                                                    ad.addDetails();
                                                }
                                                if (ans5 == 2) {
                                                    ad.viewDetails();
                                                }
                                                if (ans5 == 3) {
                                                    ad.modifyDetails();
                                                }
                                                if (ans5 == 4) {
                                                    continue;
                                                }
                                            }
                                        }

                                    } catch (HibernateException e) {
                                        if (tx != null) {
                                            tx.rollback();
                                            e.printStackTrace();
                                        }
                                    } finally {
                                        session.close();
                                    }
                                    System.out.println("Do you want to \n1.Exit \n2.Perform more admin operations?");
                                    int ans5 = sc.nextInt();
                                    if (ans5 == 1) {
                                        flag = 1;
                                        break;
                                    }

                                }
                                break;
                            case 2:
                                flag = 0;
                                while (flag == 0) {
                                    sc.nextLine();
                                    System.out.println("Enter username(email id)");
                                    String us = sc.nextLine();
                                    System.out.println("Enter password");
                                    String ps = sc.nextLine();
                                    try {
                                        cfg = new Configuration();
                                        cfg.configure("hibernate.cfg.xml");
                                        factory = cfg.buildSessionFactory();
                                        session = factory.openSession();
                                        tx=null;
                                        String sql = "select CID ,PASS from customer where email=:em";
                                        SQLQuery query = session.createSQLQuery(sql);
                                        query.setParameter("em", us);
                                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                                        List data = query.list();
                                        String p = null;
                                        if (data.isEmpty()) {
                                            System.out.println("Wrong username. Do you want to enter the \n 1.admin login  \n 2.try again?");
                                            int ans3 = sc.nextInt();
                                            if (ans3 == 1) {
                                                flag = 1;
                                                break;
                                            }
                                            if (ans3 == 2) {
                                                continue;
                                            }
                                        } else {
                                            for (Object object : data) {
                                                Map row = (Map) object;
                                                cid = (String) row.get("CID");
                                                p = (String) row.get("PASS");
                                            }
                                        }
                                        if (p.equals(ps)) {
                                            System.out.println("Login succesful. What do you want to do?\n 1.Modify profile. \n2.Bus booking \n3.Flight booking \n4.Train booking ");
                                            Customer cust = new Customer();
                                            int ans3 = sc.nextInt();
                                            switch (ans3) {
                                                case 1:
                                                    cust.modify(cid);
                                                    break;
                                                case 2:
                                                    BusBooking bb = new BusBooking();
                                                    System.out.println("Do you want to \n1.Make a bus reservation \n2.View an existing reservation");
                                                    int ans4 = sc.nextInt();
                                                    if (ans4 == 1) {
                                                        bb.makeReservation(cid);
                                                    }
                                                    if (ans4 == 2) {
                                                        bb.viewBooking(cid);
                                                    }
                                                    if (ans4 != 1 && ans4 != 2) {
                                                        System.out.println("Wrong option!");
                                                    }
                                                    break;
                                                case 4:
                                                    TrainBooking tb = new TrainBooking();
                                                    System.out.println("Do you want to \n1.Make a new train reservation \n2.View an existing reservation");
                                                    ans4 = sc.nextInt();
                                                    if (ans4 == 1) {
                                                        tb.makeReservation(cid);
                                                    }
                                                    if (ans4 == 2) {
                                                        tb.viewBooking(cid);
                                                    }
                                                    if (ans4 != 1 && ans4 != 2) {
                                                        System.out.println("Wrong option!");
                                                    }
                                                    break;
                                                case 3:
                                                    FlightBooking fb = new FlightBooking();
                                                    System.out.println("Do you want to \n1.Make a new flight reservation \n2.View an existing reservation");
                                                    ans4 = sc.nextInt();
                                                    if (ans4 == 1) {
                                                        fb.makeReservation(cid);
                                                    }
                                                    if (ans4 == 2) {
                                                        fb.viewBooking(cid);
                                                    }
                                                    if (ans4 != 1 && ans4 != 2) {
                                                        System.out.println("Wrong option!");
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("Wrong option");
                                            }
                                        }

                                    } catch (HibernateException e) {
                                        if (tx != null) {
                                            tx.rollback();
                                            e.printStackTrace();
                                        }
                                    } finally {
                                        session.close();
                                    }
                                    System.out.println("Do you want to \n1.Exit \n2.Perform more customer operations?");
                                    int ans5 = sc.nextInt();
                                    if (ans5 == 1) {
                                        flag = 1;
                                        break;
                                    }

                                }
                            }
                        System.out.println("Do you want to exit the login page?[y/n]");
                        char ans6= sc.next().charAt(0);
                        if(ans6=='y')
                        {
                            flag1=1;
                            break;
                        }
                    }
            }
            System.out.println("Do you want to exit the portal[y/n]?");
            char ans5 = sc.next().charAt(0);
            if (ans5 == 'y') {
                System.out.println("Thank you for your time!!");
                flag2 = 1;
                break;
            }

        }
    }
    }














