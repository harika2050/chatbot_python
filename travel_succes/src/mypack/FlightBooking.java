package mypack;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
@Entity
@Table(name = "Flight_booking")

public class FlightBooking {
    public FlightBooking() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FID")
    public String fid;
    @Column(name = "PID")
    public String pid;
    @Column(name = "TRANS_ID")
    public String tid;
    @Column(name = "CID")
    public String cid;
    @Column(name = "PAY_TIME")
    public Timestamp pay_time;
    @Column(name = "AMOUNT")
    public double amount;
    @Column(name = "TAX")
    public double tax;
    @Column(name = "DISCOUNT")
    public double discount;
    @Column(name = "FINAL_PRICE")
    public double final_price;

    @Column(name = "travel_date")
    public Date travelDate;

    @Column(name = "number_of_passengers")
    public int num_of_pas;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public double getCharges() {
        return final_price;
    }

    public void setCharges(double charges) {
        this.final_price = charges;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Timestamp getPay_time() {
        return pay_time;
    }

    public void setPay_time(Timestamp pay_time) {
        this.pay_time = pay_time;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
    public int getNum_of_pas()
    {
        return(num_of_pas);
    }
    public void setNum_of_pas(int num_of_pas)
    {
        this.num_of_pas= num_of_pas;
    }


    public void makeReservation(String cid) throws ParseException {
        //taking travel details from customer
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        while (flag == 0) {
            sc.nextLine();
            System.out.println("Enter the source");
            String src = sc.nextLine();
            System.out.println("Enter the destination ");
            String dest = sc.nextLine();
            System.out.println("Enter the date of travel in dd/mm/yyyy format");
            String date_of_travel = sc.nextLine();
            Date yourdate = new SimpleDateFormat("dd/mm/yyyy").parse(date_of_travel);
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
            String day_of_week = simpleDateformat.format(yourdate);
            //checking for availablity
            SimpleDateFormat sdformat= new SimpleDateFormat("yyyy-mm-dd");
            Calendar myCalendar = new GregorianCalendar(2020, 5, 9);
            Date d1 = myCalendar.getTime();
            Date d2 = yourdate;
            d1 = sdformat.parse(sdformat.format(d1));
            d2 = sdformat.parse(sdformat.format(d2));
            System.out.println(d1 + " " + d2);
            if (d1.compareTo(d2) > 0) {
                System.out.println("Invalid date entered. Enter a date at a future time");
                continue;
            }

            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory = cfg.buildSessionFactory();
            Session session = factory.openSession();
            Transaction tx = null;
            int cap = 0, book = 0;
            Time depart = null;
            String sql = "SELECT fid, airline, price, capacity, booked, depart, arrive FROM FLIGHT where src=:src_name and dest=:dest_name and dayofweek=:dow";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("src_name", src);
            query.setParameter("dest_name", dest);
            query.setParameter("dow", (String) day_of_week);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            if (data.isEmpty()) {
                System.out.println("Sorry no flights are available for the mentioned information");
                System.out.println("Do you want to check for another location or time?[y/n]");
                char ans3 = sc.next().charAt(0);
                if (ans3 == 'y') {
                    continue;
                }
                if (ans3 == 'n') {
                    System.out.println("Thank you for your time!!");
                    flag = 1;
                    break;
                }

            } else {
                System.out.println("Available Flights");

                try {
                    for (Object object : data) {
                        Map row = (Map) object;
                        System.out.println("Flight ID" + row.get("fid"));
                        System.out.println("Airline name: " + row.get("airline"));
                        System.out.println("Price: " + row.get("price"));
                        cap = (Integer) row.get("capacity");
                        book = (Integer) row.get("booked");
                        System.out.println("Number of seats left: " + (cap - book));
                        depart= (Time) row.get("depart");
                        System.out.println("Departure time"+depart);
                        System.out.println("Arrival at destination"+row.get("arrive"));
                    }
                    //tx.commit();
                } catch (HibernateException e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
            }
            String flightidd;
            int noi;
            double initcost, finalcost = 0, tax, total = 0;
            System.out.println("Do you want to check availability for another set of source and destination[y/n]");
            char ans = sc.next().charAt(0);
            if (ans == 'y') {
                continue;
            }
            if (ans == 'n') {
                System.out.println("Do you want to proceed with the purchase?[y/n]");
                char ans1 = sc.next().charAt(0);
                if (ans1 == 'n') {
                    System.out.println("Thank you for your time!!");
                    flag = 1;
                } else {
                    int flag2 = 0;
                    while (flag2 == 0) {
                        sc.nextLine();
                        System.out.println("Enter the flight id of the flight chosen");
                        flightidd = sc.nextLine();

                        System.out.println("Enter the number of individuals");
                        noi = sc.nextInt();
                        if (noi > (cap - book)) {
                            System.out.println("The requested number of seats are unavailable. Please try again");
                            continue;
                        }
                        System.out.println("Please verify your details:");
                        cfg = new Configuration();
                        cfg.configure("hibernate.cfg.xml");
                        factory = cfg.buildSessionFactory();
                        session = factory.openSession();
                        tx = null;
                        try {
                            sql = "SELECT  airline, price, src, dest  FROM Flight where fid=:flight_id";
                            query = session.createSQLQuery(sql);
                            query.setParameter("flight_id", flightidd);
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            data = query.list();
                            tx = null;
                            if (data.isEmpty()) {
                                System.out.println("You have entered the wrong flight id.Try again");
                                continue;
                            } else {
                                System.out.println("Date of travel:"+date_of_travel);
                                for (Object object : data) {
                                    Map row = (Map) object;
                                    System.out.println("Airline name: " + row.get("airline"));
                                    BigDecimal pr = (BigDecimal) row.get("price");
                                    initcost = pr.doubleValue();
                                    System.out.println("Source" + row.get("src"));
                                    System.out.println("Destination" + row.get("dest"));
                                    finalcost = noi * initcost;
                                    tax = 0.18 * finalcost;
                                    total = finalcost + tax;
                                    System.out.println("Cost per ticket:" + initcost);
                                    System.out.println("Number of individuals" + noi);
                                    System.out.println("Cost:" + finalcost);
                                    System.out.println("Tax:" + tax);
                                    System.out.println("Total cost including tax" + total);
                                }
                            }
                        } catch (HibernateException e) {
                            if (tx != null) tx.rollback();
                            e.printStackTrace();
                        } finally {
                            session.close();
                        }
                        System.out.println("Do you want to proceed to payment?[y/n]");
                        char ans2 = sc.next().charAt(0);
                        if (ans2 == 'n') {
                            flag2 = 1;
                            flag = 1;
                            break;
                        }
                        if (ans2 == 'y') {
                            int flag4 = 0;
                            while (flag4 == 0) {
                                sc.nextLine();
                                System.out.println("Enter the 12-digit card number");
                                String cardno = sc.nextLine();
                                if (cardno.length() != 12) {
                                    System.out.println("Card number is of incorrect length. try again");
                                    continue;
                                }
                                cfg = new Configuration();
                                cfg.configure("hibernate.cfg.xml");
                                factory = cfg.buildSessionFactory();
                                session = factory.openSession();
                                String cvv1 = "";
                                tx = null;
                                String pid = null;
                                try {
                                    sql = "SELECT  payment.pid, payment.bank, payment.card_type, payment.cvv, customer.firstname, customer.MIDDLENAME, customer.lastname FROM payment, customer where customer.cid=payment.cid and payment.card_no=:cno and customer.cid=:cust_id";
                                    query = session.createSQLQuery(sql);
                                    query.setParameter("cno", cardno);
                                    query.setParameter("cust_id", cid);
                                    query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                                    data = query.list();
                                    if (data.isEmpty()) {
                                        System.out.println("Wrong card details");
                                        continue;
                                    } else {

                                        for (Object object : data) {
                                            Map row = (Map) object;
                                            pid= (String) row.get("pid");
                                            System.out.println("Bank Name:" + row.get("bank"));
                                            System.out.println("Card type:" + row.get("card_type"));
                                            cvv1 = (String) row.get("cvv");
                                            System.out.println("Customer Name:" + row.get("firstname") + "  " + row.get("lastname"));
                                            System.out.println("Bank Name:" + row.get("bank"));
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
                                System.out.println("Do you want to continue[y/n]?");
                                char ans4 = sc.next().charAt(0);
                                if (ans4 == 'y') {
                                    System.out.println("Enter your cvv");
                                    sc.nextLine();
                                    String cvv2 = sc.nextLine();
                                    if (cvv1.equals(cvv2)) {
                                        System.out.println("Payment succesful!!!!");
                                        cfg=new Configuration();
                                        cfg.configure("hibernate.cfg.xml");
                                        factory=cfg.buildSessionFactory();
                                        session=factory.openSession();

                                        //making entry into the busbooking table

                                        FlightBooking bb= new FlightBooking();
                                        bb.setFid(fid);
                                        bb.setCid(cid);
                                        bb.setPid(pid);
                                        bb.setNum_of_pas(noi);
                                        Date date= new Date();
                                        //getTime() returns current time in milliseconds
                                        long time = date.getTime();
                                        //Passed the milliseconds to constructor of Timestamp class
                                        Timestamp ts = new Timestamp(time);
                                        bb.setPay_time(ts);
                                        int n= 100 + new Random().nextInt(900);
                                        bb.setTid(Integer.toString(n));
                                        bb.setAmount(finalcost);
                                        bb.setDiscount(0.0);
                                        bb.setFinal_price(total);
                                        bb.setTravelDate(yourdate);
                                        tx=session.beginTransaction();
                                        session.save(bb);
                                        tx.commit();
                                        tx=session.beginTransaction();
                                        int newbook= book-noi;
                                        sql = "update flight set BOOKED=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";
                                        Time dt= depart;
                                        query = session.createSQLQuery(sql)
                                                .setParameter("x", newbook)
                                                .setParameter("y",fid)
                                                .setParameter("z",dt)
                                                .setParameter("a",day_of_week);
                                        int result = query.executeUpdate();
                                        tx.commit();

                                        session.close();
                                        factory.close();


                                        flag = 1;
                                        break;
                                    } else {
                                        System.out.println("Payment has failed as the credentials entered did not match. Please try again");
                                        continue;
                                    }
                                }
                                if (ans4 == 'n') {
                                    System.out.println("Thank you for your time!");
                                    flag2 = 1;
                                    flag = 1;
                                    break;
                                }


                            }
                            flag2 = 1;
                        }
                    }

                }
            }
            System.out.println("Thank you for your time");
            flag = 1;

        }
    }
    public void viewBooking(String cid) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        String cvv1 = "";
        Transaction tx = null;
        try {
            String sql = "SELECT customer.firstname, customer.lastname, flight.airline, flight.src, flight.dest, flight.depart, flight.arrive, flight.dayofweek, flight_booking.number_of_passengers, flight_booking.travel_date, flight_booking.final_price from customer, flight, flight_booking where customer.cid=:cust_id and flight_booking.cid= customer.cid and flight_booking.fid= flight.fid";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cust_id", cid);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            tx = session.beginTransaction();

            for (Object object : data) {
                Map row = (Map) object;
                System.out.println("Hello");
                System.out.println("Name: "+row.get("firstname")+" "+row.get("lastname"));
                System.out.println("Bus name: "+row.get("bname"));
                System.out.println("Source: "+row.get("src"));
                System.out.println("Destination: "+row.get("dest"));
                System.out.println("Departure time: "+row.get("depart_time"));
                System.out.println("Arrival time: "+row.get("arrive_time"));
                System.out.println("Day of the week"+ row.get("dayofweek"));
                System.out.println("Number of passengers"+row.get("number_of_passengers"));
                System.out.println("Date of travel "+row.get("travel_date"));
                System.out.println("Final price:"+row.get("final_price"));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        // TODO implement here
    }

}