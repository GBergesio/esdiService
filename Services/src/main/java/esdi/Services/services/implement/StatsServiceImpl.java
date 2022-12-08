package esdi.Services.services.implement;

import esdi.Services.dtos.stats.StatsMore;
import esdi.Services.dtos.stats.StatsOBT;
import esdi.Services.dtos.stats.StatsOBW;
import esdi.Services.dtos.stats.StatsTopDevice;
import esdi.Services.enums.OrderType;
import esdi.Services.enums.Status;
import esdi.Services.enums.StatusBudget;
import esdi.Services.enums.UserType;
import esdi.Services.mappers.OrderMapper;
import esdi.Services.models.Order;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.*;
import esdi.Services.services.JwtUserDetailsService;
import esdi.Services.services.StatsService;
import org.apache.tomcat.jni.Local;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public ResponseEntity<?> stats(Authentication authentication) {
        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            StatsMore statsMore = new StatsMore();

            if(company != null){
                int pb = budgetRepository.findAllByCompany(company).stream().filter(b -> b.getStatusBudget().equals(StatusBudget.ON_HOLD)).collect(Collectors.toList()).size();
                int ab = budgetRepository.findAllByCompany(company).stream().filter(b -> b.getStatusBudget().equals(StatusBudget.APPROVED)).collect(Collectors.toList()).size();
                int rb = budgetRepository.findAllByCompany(company).stream().filter(b -> b.getStatusBudget().equals(StatusBudget.REJECTED)).collect(Collectors.toList()).size();
                int po = orderRepository.findAllByCompany(company).stream().filter(b -> b.getStatus().equals(Status.ON_HOLD)).collect(Collectors.toList()).size();
                int orReady = orderRepository.findAllByCompany(company).stream().filter(order -> order.getStatus().equals(Status.READY_R)).collect(Collectors.toList()).size();
                int orWR = orderRepository.findAllByCompany(company).stream().filter(order -> order.getStatus().equals(Status.WITHDRAWN_R)).collect(Collectors.toList()).size();
                int oWr = orderRepository.findAllByCompany(company).stream().filter(order -> order.getStatus().equals(Status.READY_WR)).collect(Collectors.toList()).size();
                int oWWR = orderRepository.findAllByCompany(company).stream().filter(order -> order.getStatus().equals(Status.WITHDRAWN_WR)).collect(Collectors.toList()).size();
                int cl  = clientRepository.findAllByCompany(company).size();
                int to = orderRepository.findAllByCompany(company).size();
                int rep = orReady + orWR;
                int toUn = oWr + oWWR;
                String name = company.getName();
                String role = company.getUserType().toString();
                String sector = company.getSector();

                statsMore.setPendingBudgets(pb);
                statsMore.setApprovedBudgets(ab);
                statsMore.setRejectedBudgets(rb);
                statsMore.setPendingOrders(po);
                statsMore.setClients(cl);
                statsMore.setTotalOrders(to);
                statsMore.setRepaired(rep);
                statsMore.setUnrepaired(toUn);
                statsMore.setUserName(name);
                statsMore.setRole(role);
                statsMore.setSector(sector);
                return new ResponseEntity<>(statsMore, HttpStatus.OK);

            }

            if(staff !=null){
                Company companyStaff = companyRepository.findByStaffs(staff);

                int pb = budgetRepository.findAllByCompany(companyStaff).stream().filter(b -> b.getStatusBudget().equals(StatusBudget.ON_HOLD)).collect(Collectors.toList()).size();
                int ab = budgetRepository.findAllByCompany(companyStaff).stream().filter(b -> b.getStatusBudget().equals(StatusBudget.APPROVED)).collect(Collectors.toList()).size();
                int rb = budgetRepository.findAllByCompany(companyStaff).stream().filter(b -> b.getStatusBudget().equals(StatusBudget.REJECTED)).collect(Collectors.toList()).size();
                int po = orderRepository.findAllByCompany(companyStaff).stream().filter(b -> b.getStatus().equals(Status.ON_HOLD)).collect(Collectors.toList()).size();
                int orReady = orderRepository.findAllByCompany(companyStaff).stream().filter(order -> order.getStatus().equals(Status.READY_R)).collect(Collectors.toList()).size();
                int orWR = orderRepository.findAllByCompany(companyStaff).stream().filter(order -> order.getStatus().equals(Status.WITHDRAWN_R)).collect(Collectors.toList()).size();
                int oWr = orderRepository.findAllByCompany(companyStaff).stream().filter(order -> order.getStatus().equals(Status.READY_WR)).collect(Collectors.toList()).size();
                int oWWR = orderRepository.findAllByCompany(companyStaff).stream().filter(order -> order.getStatus().equals(Status.WITHDRAWN_WR)).collect(Collectors.toList()).size();
                int cl  = clientRepository.findAllByCompany(companyStaff).size();
                int to = orderRepository.findAllByCompany(companyStaff).size();
                int rep = orReady + orWR;
                int toUn = oWr + oWWR;
                String name = staff.getFirstName();
                String role = staff.getUserType().toString();
                String sector = companyStaff.getSector();

                statsMore.setPendingBudgets(pb);
                statsMore.setApprovedBudgets(ab);
                statsMore.setRejectedBudgets(rb);
                statsMore.setPendingOrders(po);
                statsMore.setClients(cl);
                statsMore.setTotalOrders(to);
                statsMore.setRepaired(rep);
                statsMore.setUnrepaired(toUn);
                statsMore.setUserName(name);
                statsMore.setRole(role);
                statsMore.setSector(sector);

                return new ResponseEntity<>(statsMore, HttpStatus.OK);
            }

            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> ordersByWeekT(Authentication authentication) {
        return null;
    }

//    @Override
//    public ResponseEntity<?> ordersByWeekT(Authentication authentication) {
//
//        try {
//            Company company = companyRepository.findByUsername(authentication.getName());
//            Staff staff = staffRepository.findByUser(authentication.getName());
//
//            LocalDateTime now = LocalDateTime.now();
//            LocalDateTime mon = now.minusDays(1);
//            DayOfWeek tue = now.with((DayOfWeek.TUESDAY)).getDayOfWeek();
//            DayOfWeek wed = now.with((DayOfWeek.WEDNESDAY)).getDayOfWeek();
//            DayOfWeek thu = now.with((DayOfWeek.THURSDAY)).getDayOfWeek();
//            DayOfWeek fri = now.with((DayOfWeek.FRIDAY)).getDayOfWeek();
//            DayOfWeek sat = now.with((DayOfWeek.SATURDAY)).getDayOfWeek();
//            DayOfWeek sun = now.with((DayOfWeek.SUNDAY)).getDayOfWeek();
//
//
//            if (company != null) {
//                List<Order> orders = orderRepository.findAllByCompany(company);
//
//                int obdmo = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(mon)).collect(Collectors.toList()).size();
//                int obdtu = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(tue)).collect(Collectors.toList()).size();
//                int obdwe = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(wed)).collect(Collectors.toList()).size();
//                int obdth = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(thu)).collect(Collectors.toList()).size();
//                int obdfr = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(fri)).collect(Collectors.toList()).size();
//                int obdsa = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(sat)).collect(Collectors.toList()).size();
//                int obdsu = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(sun)).collect(Collectors.toList()).size();
//
//                StatsOBW statsOBW = new StatsOBW();
//                statsOBW.setMo(obdmo);
//                statsOBW.setTu(obdtu);
//                statsOBW.setWe(obdwe);
//                statsOBW.setTh(obdth);
//                statsOBW.setFr(obdfr);
//                statsOBW.setSa(obdsa);
//                statsOBW.setSu(obdsu);
//
//                return new ResponseEntity<>(statsOBW, HttpStatus.OK);
//            }
//
//            if (staff != null) {
//                List<Order> orders = orderRepository.findAllByCompany(staff.getCompany());
//
//                int obdmo = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(mon)).collect(Collectors.toList()).size();
//                int obdtu = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(tue)).collect(Collectors.toList()).size();
//                int obdwe = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(wed)).collect(Collectors.toList()).size();
//                int obdth = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(thu)).collect(Collectors.toList()).size();
//                int obdfr = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(fri)).collect(Collectors.toList()).size();
//                int obdsa = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(sat)).collect(Collectors.toList()).size();
//                int obdsu = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(sun)).collect(Collectors.toList()).size();
//
//                StatsOBW statsOBW = new StatsOBW();
//                statsOBW.setMo(obdmo);
//                statsOBW.setTu(obdtu);
//                statsOBW.setWe(obdwe);
//                statsOBW.setTh(obdth);
//                statsOBW.setFr(obdfr);
//                statsOBW.setSa(obdsa);
//                statsOBW.setSu(obdsu);
//
//                return new ResponseEntity<>(statsOBW, HttpStatus.OK);
//            }
//            else{
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//
//    }

    @Override
    public ResponseEntity<?> ordersByWeek(Authentication authentication) {

        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            int now = LocalDateTime.now().getDayOfYear();
            int yesterday = LocalDateTime.now().minusDays(3).getDayOfYear();

            if (company != null) {
                List<Order> orders = orderRepository.findAllByCompany(company);

                int ontoday = orders.stream().filter(o -> o.getJoinDate().equals(now)).collect(Collectors.toList()).size();
                int onYesterday = orders.stream().filter(o -> o.getJoinDate().equals(now)).collect(Collectors.toList()).size();
//                int onTwo = orders.stream().filter(o -> o.getJoinDate().equals(now.minusDays(2))).collect(Collectors.toList()).size();
//                int onThree = orders.stream().filter(o ->o.getJoinDate().equals(now.minusDays(3))).collect(Collectors.toList()).size();
//                int onFour = orders.stream().filter(o -> o.getJoinDate().equals(now.minusDays(4))).collect(Collectors.toList()).size();
//                int onFive = orders.stream().filter(o -> o.getJoinDate().equals(now.minusDays(5))).collect(Collectors.toList()).size();
//                int onSix = orders.stream().filter(o -> o.getJoinDate().equals(now.minusDays(6))).collect(Collectors.toList()).size();

                StatsOBW statsOBW = new StatsOBW();
                statsOBW.setToday(ontoday);
                statsOBW.setYesterday(onYesterday);
//                statsOBW.setTwo(3);
//                statsOBW.setThree(onThree);
//                statsOBW.setFour(onFour);
//                statsOBW.setFive(onFive);
//                statsOBW.setSix(onSix);

                return new ResponseEntity<>(statsOBW, HttpStatus.OK);
            }

//            if (staff != null) {
//                List<Order> orders = orderRepository.findAllByCompany(staff.getCompany());
//
//                int ontoday = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(now)).collect(Collectors.toList()).size();
//                int onYesterday = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(yesterday)).collect(Collectors.toList()).size();
//                int onTwo = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(two)).collect(Collectors.toList()).size();
//                int onThree = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(three)).collect(Collectors.toList()).size();
//                int onFour = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(fo)).collect(Collectors.toList()).size();
//                int onFive = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(fi)).collect(Collectors.toList()).size();
//                int onSix = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(six)).collect(Collectors.toList()).size();
//
//                StatsOBW statsOBW = new StatsOBW();
//                statsOBW.setToday(ontoday);
//                statsOBW.setYesterday(onYesterday);
//                statsOBW.setTwo(onTwo);
//                statsOBW.setThree(onThree);
//                statsOBW.setFour(onFour);
//                statsOBW.setFive(onFive);
//                statsOBW.setSix(onSix);
//
//                return new ResponseEntity<>(statsOBW, HttpStatus.OK);
//            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> oBt(Authentication authentication) {
        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());
            StatsOBT statsOBTS = new StatsOBT();

            if(company != null){
                List<Staff> staffList = staffRepository.findAllByCompany(company).stream().filter(s->s.getUserType().equals(UserType.TECHNICIAN)).collect(Collectors.toList());

                Set<Integer> qtyOrders = staffList.stream().map(s-> s.getOrders().size()).collect(Collectors.toSet());
                List<String> staffNames = staffList.stream().map(s-> s.getFirstName()).collect(Collectors.toList());

                statsOBTS.setStaffName(staffNames);
                statsOBTS.setQty(qtyOrders);

                return new ResponseEntity<>(statsOBTS,HttpStatus.OK);
            }
            if(staff != null){
                Company companyStaff = staff.getCompany();
                List<Staff> staffList = staffRepository.findAllByCompany(companyStaff).stream().filter(s->s.getUserType().equals(UserType.TECHNICIAN)).collect(Collectors.toList());

                Set<Integer> qtyOrders = staffList.stream().map(s-> s.getOrders().size()).collect(Collectors.toSet());
                List<String> staffNames = staffList.stream().map(s-> s.getFirstName()).collect(Collectors.toList());

                statsOBTS.setStaffName(staffNames);
                statsOBTS.setQty(qtyOrders);

                return new ResponseEntity<>(statsOBTS,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> owBt(Authentication authentication) {
        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());
            StatsOBT statsOBTS = new StatsOBT();

            if(company != null){
                List<Staff> staffList = staffRepository.findAllByCompany(company).stream().filter(s->s.getUserType().equals(UserType.TECHNICIAN)).collect(Collectors.toList());

                List<String> staffNames = staffList.stream().map(s-> s.getFirstName()).collect(Collectors.toList());
                Set<Integer> qtyOrders = staffList.stream().map(s-> s.getOrders().stream().filter(o->o.getOrderType().equals(OrderType.WARRANTY)).collect(Collectors.toList()).size()).collect(Collectors.toSet());

                statsOBTS.setStaffName(staffNames);
                statsOBTS.setQty(qtyOrders);

                return new ResponseEntity<>(statsOBTS,HttpStatus.OK);
            }
            if(staff != null){
                Company companyStaff = staff.getCompany();
                List<Staff> staffList = staffRepository.findAllByCompany(companyStaff).stream().filter(s->s.getUserType().equals(UserType.TECHNICIAN)).collect(Collectors.toList());

                List<String> staffNames = staffList.stream().map(s-> s.getFirstName()).collect(Collectors.toList());
                Set<Integer> qtyOrders = staffList.stream().map(s-> s.getOrders().stream().filter(o->o.getOrderType().equals(OrderType.WARRANTY)).collect(Collectors.toList()).size()).collect(Collectors.toSet());

                statsOBTS.setStaffName(staffNames);
                statsOBTS.setQty(qtyOrders);

                return new ResponseEntity<>(statsOBTS,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> topDevice(Authentication authentication) {
        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());
            StatsTopDevice topDevices = new StatsTopDevice();

            if(company != null){
                List<Order> orders = orderRepository.findAllByCompany(company);
                List<String> devOr = orders.stream().map(o->o.getDevice().getCategory().getName()).collect(Collectors.toList());


                return new ResponseEntity<>(devOr,HttpStatus.OK);
            }

            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


}
