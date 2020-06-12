package mypack;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
@Entity
@Table(name = "flight")
public class Flight implements Serializable{

    public Flight() {
    }
    @Id
    @Column(name = "FID")
    public String fid;
    @Column(name = "AIRLINE")
    public String airline;
    @Column(name = "FNUMBER")
    public String fnumber;
    @Id
    @Column(name = "DAYOFWEEK")
    public String dayofweek;
    @Column(name = "CAPACITY")
    public int capacity;
    @Column(name = "PRICE")
    public double price;
    @Id
    @Column(name = "DEPART")
    public Time departTime;
    @Column(name = "ARRIVE")
    public Time arriveTime;
    @Column(name = "SRC")
    public String source;
    @Column(name = "DEST")
    public String destination;
    @Column(name = "BOOKED")
    public int booked;


    public int hashCode() {
        return (int)this.fid.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Flight)) return false;
        Flight pk = (Flight) obj;
        return pk.fid.equals(this.fid) && pk.dayofweek.equals(this.dayofweek) && pk.departTime.equals(this.departTime);
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Time getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Time departTime) {
        this.departTime = departTime;
    }

    public Time getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public String getFnumber() {
        return fnumber;
    }

    public void setFnumber(String fnumber) {
        this.fnumber = fnumber;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public void addFlight() throws ParseException {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Flight fl= new Flight();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the flight id");
        String t = sc.nextLine();
        fl.setFid(t);
        System.out.println("Enter the name of the airline");
        t = sc.nextLine();
        fl.setAirline(t);

        System.out.println("Enter the flight number");
        t = sc.nextLine();
        fl.setFnumber(t);

        sc.nextLine();
        System.out.println("Enter the departure time");

        t= sc.nextLine();
        Time time= Time.valueOf(t);
        fl.setDepartTime(time);
        sc.nextLine();
        System.out.println("Enter the arrival time");
        String t3= sc.nextLine();
        Time time2= Time.valueOf(t3);
        fl.setArriveTime(time2);

        System.out.println("Enter the capacity");
        int c = sc.nextInt();
        fl.setCapacity(c);

        System.out.println("Enter the number of seats booked");
        c = sc.nextInt();
        fl.setBooked(c);

        System.out.println("Enter the price per ticket");
        double d = sc.nextDouble();
        fl.setPrice(d);

        sc.nextLine();
        System.out.println("Enter the source location");
        t = sc.nextLine();
        fl.setSource(t);

        sc.nextLine();
        System.out.println("Enter the destination location");
        t = sc.nextLine();
        fl.setDestination(t);

        System.out.println("Enter the day of the week the airplane flies");
        t = sc.nextLine();
        fl.setDayofweek(t);
        Transaction tx=session.beginTransaction();
        session.save(fl);
        System.out.println("success");
        tx.commit();
        session.close();
        factory.close();
        // TODO implement here
    }
    public void deleteFlight() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("Enter the flight id of the bus to be deleted");
        String flid= sc.nextLine();
        System.out.println("Enter the departure time of the bus to be deleted");
        String dtime= sc.nextLine();
        Time dt= Time.valueOf(dtime);
        System.out.println("Enter the day of the week the bus runs of the bus to be deleted");
        String dow= sc.nextLine();
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        String sql = "delete from flight where FID= :y and DEPART= :z and DAYOFWEEK= :a";

        SQLQuery query = session.createSQLQuery(sql)

                .setParameter("y",flid)
                .setParameter("z",dt)
                .setParameter("a",dow);
        int result = query.executeUpdate();
        tx.commit();
        session.close();
        factory.close();

        // TODO implement here
    }
    public void modifyFlight() {
        Scanner sc= new Scanner(System.in);
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("Enter the flight id of the flight to be modified");
        String fid= sc.nextLine();
        System.out.println("Enter the departure time of the flight to be modified");
        String depart= sc.nextLine();
        Time depart_time= Time.valueOf(depart);
        System.out.println("Enter the day of the week on which the flight runs");
        String dow= sc.nextLine();
        System.out.println("Enter the attribute of flight whose contents are to be modified :fnumber, airline, dayofweek,price, source, destination, Arrivetime, DepartTime, capacity, booked");
        String obj=sc.nextLine();
        switch (obj)
        {
            case "fnumber":
                System.out.println("Enter the new Flight number");
                int z=sc.nextInt();
                String sql = "update flight set fnumber=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";

                SQLQuery query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                int result = query.executeUpdate();
                tx.commit();

                break;
            case "airline":
                System.out.println("Enter the new value");
                String z1=sc.next();
                sql = "update flight set bname=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "dayofweek":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update flight set DAYOFWEEK=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "price":
                System.out.println("Enter the new value");
                double z3=sc.nextDouble();
                sql = "update flight set price=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z3)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "source":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update flight set SRC=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "destination":
                System.out.println("Enter the new value");
                z1=sc.nextLine();
                sql = "update flight set dest=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", z1)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "ArriveTime":
                System.out.println("Enter the new value");
                String t=sc.next();
                Time at=Time.valueOf(t);
                sql = "update flight set arrive=:x where FLIGHTD= :y and DEPART= :z and DAYOFWEEK= :a";
                query = session.createSQLQuery(sql)
                        .setParameter("x", at)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
            case "DepartTime":
                System.out.println("Enter the new value");
                t=sc.nextLine();
                Time dt= Time.valueOf(t);
                sql = "update flight set depart=:x where FID= y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", dt)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "capacity":
                System.out.println("Enter the new value");
                z=sc.nextInt();
                sql = "update flight set capacity=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();
                break;
            case "booked":
                System.out.println("Enter the new value");
                z=sc.nextInt();
                sql = "update flight set BOOKED=:x where FID= :y and DEPART= :z and DAYOFWEEK= :a";

                query = session.createSQLQuery(sql)
                        .setParameter("x", z)
                        .setParameter("y",fid)
                        .setParameter("z",depart_time)
                        .setParameter("a",dow);
                result = query.executeUpdate();
                tx.commit();

                break;
        }
        System.out.println("Object Update successfull!");
        session.close();
        factory.close();

    }
    public void viewFlight() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Scanner sc= new Scanner(System.in);
        Transaction tx = null;
        System.out.println("Enter the flight id you want to view");
        String fid= sc.nextLine();
        try {
            String sql = "select airline, fnumber, price, src, dest, capacity, booked, depart, arrive, dayofweek  from flight where fid=:flighttidd";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("flighttidd",fid);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();

            for (Object object : data) {
                Map row = (Map) object;
                System.out.println("Flight id "+fid);
                System.out.println("Airline name: "+row.get("airline"));
                System.out.println("Flight number:"+ row.get("fnumber"));
                System.out.println("Price:"+ row.get("price"));
                System.out.println("Source: "+row.get("src"));
                System.out.println("Destination: "+row.get("dest"));
                System.out.println("Capacity"+row.get("capacity"));
                System.out.println("Booked"+row.get("booked"));
                System.out.println("Departure time: "+row.get("depart"));
                System.out.println("Arrival time: "+row.get("arrive"));
                System.out.println("Day of the week"+ row.get("dayofweek"));

            }
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