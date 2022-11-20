package esdi.Services.services.implement;

import esdi.Services.dtos.stats.StatsOBT;
import esdi.Services.dtos.stats.StatsOBW;
import esdi.Services.enums.Status;
import esdi.Services.mappers.OrderMapper;
import esdi.Services.models.Order;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.ClientRepository;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.OrderRepository;
import esdi.Services.repositories.StaffRepository;
import esdi.Services.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    CompanyRepository companyRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ClientRepository clientRepository;


    @Override
    public ResponseEntity<?> totalOrders(Authentication authentication) {

        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            if(company != null){
                List<Order> orders = orderRepository.findAllByCompany(company);
                double size = orders.size();

                return new ResponseEntity<>(size, HttpStatus.OK);
            }

            if(staff !=null){
                Company companyStaff = companyRepository.findByStaffs(staff);
                List<Order> orders = orderRepository.findAllByCompany(companyStaff);
                double size = orders.size();
                return new ResponseEntity<>(size, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> totalRepairs(Authentication authentication) {
        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            if(company != null){
                List<Order> ordersReady = orderRepository.findAllByCompany(company).stream().filter(order -> order.getStatus().equals(Status.READY_R)).collect(Collectors.toList());
                List<Order> ordersWR = orderRepository.findAllByCompany(company).stream().filter(order -> order.getStatus().equals(Status.WITHDRAWN_R)).collect(Collectors.toList());
                double size = ordersReady.size() + ordersWR.size();
                return new ResponseEntity<>(size, HttpStatus.OK);
            }

            if(staff !=null){
                Company companyStaff = companyRepository.findByStaffs(staff);
                List<Order> ordersReady = orderRepository.findAllByCompany(companyStaff).stream().filter(order -> order.getStatus().equals(Status.READY_R)).collect(Collectors.toList());
                List<Order> ordersWR = orderRepository.findAllByCompany(companyStaff).stream().filter(order -> order.getStatus().equals(Status.WITHDRAWN_R)).collect(Collectors.toList());
                double size = ordersReady.size() + ordersWR.size();
                return new ResponseEntity<>(size, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> totalWr(Authentication authentication) {
        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            if(company != null){
                List<Order> ordersReady = orderRepository.findAllByCompany(company).stream().filter(order -> order.getStatus().equals(Status.READY_WR)).collect(Collectors.toList());
                List<Order> ordersWWR = orderRepository.findAllByCompany(company).stream().filter(order -> order.getStatus().equals(Status.WITHDRAWN_WR)).collect(Collectors.toList());
                double size = ordersReady.size() + ordersWWR.size();
                return new ResponseEntity<>(size, HttpStatus.OK);
            }

            if(staff !=null){
                Company companyStaff = companyRepository.findByStaffs(staff);
                List<Order> ordersReady = orderRepository.findAllByCompany(companyStaff).stream().filter(order -> order.getStatus().equals(Status.READY_WR)).collect(Collectors.toList());
                List<Order> ordersWWR = orderRepository.findAllByCompany(companyStaff).stream().filter(order -> order.getStatus().equals(Status.WITHDRAWN_WR)).collect(Collectors.toList());
                double size = ordersReady.size() + ordersWWR.size();
                return new ResponseEntity<>(size, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> totalClients(Authentication authentication) {
        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            if(company != null){
                List<Client> clients = clientRepository.findAllByCompany(company);
                double size = clients.size();
                return new ResponseEntity<>(size, HttpStatus.OK);
            }

            if(staff !=null){
                Company companyStaff = companyRepository.findByStaffs(staff);
                List<Client> clients = clientRepository.findAllByCompany(companyStaff);
                double size = clients.size();

                return new ResponseEntity<>(size, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> totalOrdersByTechnician(Authentication authentication) {
        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            if(company != null){
                List<StatsOBT> statsOBTS = new ArrayList<>();
                List<Staff> staffList = company.getStaffs();
                List<String> names = staffList.stream().map(s-> s.getFirstName()).collect(Collectors.toList());
                List<Integer> qtyOrders = staffList.stream().map(s-> s.getOrders().size()).collect(Collectors.toList());

                for(String name : names){
                    StatsOBT statsOBT = new StatsOBT();
                    statsOBT.setTechnician(name);
                    for(int q : qtyOrders){
                        statsOBT.setQty(q);
                    }
                    statsOBTS.add(statsOBT);
                }
                return new ResponseEntity<>(statsOBTS,HttpStatus.OK);
            }

            if(staff !=null){
                Company companyStaff = companyRepository.findByStaffs(staff);

                List<StatsOBT> statsOBTS = new ArrayList<>();
                List<Staff> staffList = companyStaff.getStaffs();
                List<String> names = staffList.stream().map(s-> s.getFirstName()).collect(Collectors.toList());
                List<Integer> qtyOrders = staffList.stream().map(s-> s.getOrders().size()).collect(Collectors.toList());

                for(String name : names){
                    StatsOBT statsOBT = new StatsOBT();
                    statsOBT.setTechnician(name);
                    for(int q : qtyOrders){
                        statsOBT.setQty(q);
                    }
                    statsOBTS.add(statsOBT);
                }
                return new ResponseEntity<>(statsOBTS,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> ordersByWeek(Authentication authentication) {

        try {
            Company company = companyRepository.findByUsername(authentication.getName());
            Staff staff = staffRepository.findByUser(authentication.getName());

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime mon = now.minusDays(1);
            DayOfWeek tue = now.with((DayOfWeek.TUESDAY)).getDayOfWeek();
            DayOfWeek wed = now.with((DayOfWeek.WEDNESDAY)).getDayOfWeek();
            DayOfWeek thu = now.with((DayOfWeek.THURSDAY)).getDayOfWeek();
            DayOfWeek fri = now.with((DayOfWeek.FRIDAY)).getDayOfWeek();
            DayOfWeek sat = now.with((DayOfWeek.SATURDAY)).getDayOfWeek();
            DayOfWeek sun = now.with((DayOfWeek.SUNDAY)).getDayOfWeek();


            if (company != null) {
                List<Order> orders = orderRepository.findAllByCompany(company);

                int obdmo = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(mon)).collect(Collectors.toList()).size();
                int obdtu = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(tue)).collect(Collectors.toList()).size();
                int obdwe = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(wed)).collect(Collectors.toList()).size();
                int obdth = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(thu)).collect(Collectors.toList()).size();
                int obdfr = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(fri)).collect(Collectors.toList()).size();
                int obdsa = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(sat)).collect(Collectors.toList()).size();
                int obdsu = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(sun)).collect(Collectors.toList()).size();

                StatsOBW statsOBW = new StatsOBW();
                statsOBW.setMo(obdmo);
                statsOBW.setTu(obdtu);
                statsOBW.setWe(obdwe);
                statsOBW.setTh(obdth);
                statsOBW.setFr(obdfr);
                statsOBW.setSa(obdsa);
                statsOBW.setSu(obdsu);

                return new ResponseEntity<>(statsOBW, HttpStatus.OK);
            }

            if (staff != null) {
                List<Order> orders = orderRepository.findAllByCompany(staff.getCompany());

                int obdmo = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(mon)).collect(Collectors.toList()).size();
                int obdtu = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(tue)).collect(Collectors.toList()).size();
                int obdwe = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(wed)).collect(Collectors.toList()).size();
                int obdth = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(thu)).collect(Collectors.toList()).size();
                int obdfr = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(fri)).collect(Collectors.toList()).size();
                int obdsa = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(sat)).collect(Collectors.toList()).size();
                int obdsu = orders.stream().filter(o -> o.getJoinDate().getDayOfWeek().equals(sun)).collect(Collectors.toList()).size();

                StatsOBW statsOBW = new StatsOBW();
                statsOBW.setMo(obdmo);
                statsOBW.setTu(obdtu);
                statsOBW.setWe(obdwe);
                statsOBW.setTh(obdth);
                statsOBW.setFr(obdfr);
                statsOBW.setSa(obdsa);
                statsOBW.setSu(obdsu);

                return new ResponseEntity<>(statsOBW, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
