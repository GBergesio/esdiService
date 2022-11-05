package esdi.Services.controllers;

import esdi.Services.dtos.request.OrderRequest;
import esdi.Services.dtos.request.ServiceArtRequest;
import esdi.Services.dtos.request.StaffRequest;
import esdi.Services.services.*;
import esdi.Services.services.budget.BudgetService;
import esdi.Services.services.budget.OptionBudgetService;
import esdi.Services.services.budget.OptionComponentService;
import esdi.Services.services.devices.DeviceCategoryService;
import esdi.Services.services.devices.DeviceModelService;
import esdi.Services.services.devices.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SAC")
public class SuperAdminController {

    @Autowired
    OrderService orderService;

    @Autowired
    StaffService staffService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    DolarService dolarService;

    @Autowired
    ProductService productService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    DeviceCategoryService deviceCategoryService;

    @Autowired
    DeviceModelService deviceModelService;

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BudgetService budgetService;

    @Autowired
    OptionBudgetService optionBudgetService;

    @Autowired
    OptionComponentService optionComponentService;

    @Autowired
    ClientService clientService;


//    @GetMapping("/orders")
//    ResponseEntity<?> getAllOrders(){
//        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
//    }
//
//    @GetMapping("/orders/{id}")
//    ResponseEntity<?> getOrderById(@PathVariable Long id){
//        return orderService.findById(id);
//    }
//
//    @GetMapping("/orders/{orderNumber}")
//    ResponseEntity<?> getOrderByNumber(@PathVariable int orderNumber){
//        return orderService.findByNumber(orderNumber);
//    }
//
//    @Transactional
//    @PostMapping("/orders")
//    ResponseEntity<?> newOrder(@RequestBody OrderRequest orderRequest, @RequestParam String dni, @RequestParam Long idDevice){
//        return orderService.createOrder(orderRequest, dni, idDevice);
//    }
//
//    @Transactional
//    @PatchMapping("/orders/modify")
//    ResponseEntity<?> updateOrder(Long idOrder,Long idDevice, Long idTechnician, OrderRequest orderRequest){
//        return orderService.updateOrder(idOrder,idDevice, idTechnician, orderRequest);
//    }
//
//    @GetMapping("/services")
//    ResponseEntity<?> getAllServices(){
//        return new ResponseEntity<>(serviceService.findAllDTO(), HttpStatus.OK);
//    }
//
//    @GetMapping("/services/{id}")
//    ResponseEntity<?> getServicesById(@PathVariable Long id){
//        return serviceService.findById(id);
//    }
//
//    @PostMapping("/services")
//    ResponseEntity<?> newService(@RequestBody ServiceArtRequest serviceArtRequest){
//        return serviceService.createService(serviceArtRequest);
//    }
//    @DeleteMapping("/services/{id}")
//    ResponseEntity<?> deleteService(@PathVariable Long id) {
//        return serviceService.deleteService(id);
//    }
//
//    @GetMapping("/products")
//    ResponseEntity<?> getAllProducts(){
//        return new ResponseEntity<>(productService.findAllDTO(), HttpStatus.OK);
//    }
//
//    @GetMapping("/products/pn/{productNumber}")
//    ResponseEntity<?> getProductByPN(@PathVariable String productNumber){
//        return productService.findPN(productNumber);
//    }
//
//    @GetMapping("/products/id/{id}")
//    ResponseEntity<?> getProductByID(@PathVariable Long id){
//        return productService.findById(id);
//    }
//
//    @GetMapping("/dollar")
//    ResponseEntity<?> getAllDollars(){
//        return new ResponseEntity<>(dolarService.allDollars(), HttpStatus.OK);
//    }
//
//    @GetMapping("/staff")
//    public ResponseEntity<?> getAllStaffs() {
//        return staffService.getAllStaffs();
//    }
//
//    @GetMapping("/staff/{id}")
//    public ResponseEntity<?> getStaffById(@PathVariable Long id) {
//        return staffService.getStaffById(id);
//    }
//
//    @GetMapping("/staff/dni/{dni}")
//    public ResponseEntity<?> getStaffByDni(@PathVariable String dni) {
//        return staffService.getStaffByDni(dni);
//    }
//
//    @GetMapping("/staff/user/{user}")
//    public ResponseEntity<?> getStaffByUser(@PathVariable String user) {
//        return staffService.getStaffByUser(user);
//    }
//
//    @PostMapping("/staff")
//    public ResponseEntity<?> createStaff(@RequestBody StaffRequest staffRequest, Long id) {
//        return staffService.createStaff(staffRequest, id);
//    }
//
//    @PatchMapping("/staff/{id}")
//    public ResponseEntity<?> updateStaff(@PathVariable Long id, @RequestBody StaffRequest staffRequest) {
//        return staffService.updateStaff(id, staffRequest);
//    }
//
//    @DeleteMapping("/staff/{id}")
//    public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
//        return staffService.deleteStaff(id);
//    }
//
//    @GetMapping("/devices")
//    ResponseEntity<?> getAllDevices(){
//        return deviceService.allDevices();
//    }
//
//    @GetMapping("/devices/{id}")
//    ResponseEntity<?> getDeviceById(@PathVariable Long id){
//        return deviceService.findById(id);
//    }
//
//    @GetMapping("/deviceCategories")
//    ResponseEntity<?> getAllDeviceCategories(){
//        return deviceCategoryService.allDeviceCategory();
//    }
//
//    @GetMapping("/deviceCategories/{id}")
//    ResponseEntity<?> getDeviceCategoryById(@PathVariable Long id){
//        return deviceCategoryService.findById(id);
//    }
//
//    @GetMapping("/deviceModel")
//    ResponseEntity<?> getAllDeviceModels(){
//        return deviceModelService.allDeviceModel();
//    }
//
//    @GetMapping("/deviceModel/{id}")
//    ResponseEntity<?> getDeviceModelById(@PathVariable Long id){
//        return deviceModelService.findById(id);
//    }
//
//    @GetMapping("/brands")
//    ResponseEntity<?> getAllBrands(){
//        return new ResponseEntity<>(brandService.findAllDTO(), HttpStatus.OK);
//    }
//
//    @GetMapping("/brands/{id}")
//    ResponseEntity<?> getBrandById(@PathVariable Long id) {
//        return brandService.findById(id);
//    }
//
//    @GetMapping("/categories")
//    ResponseEntity<?> getAllCategories() {
//        return categoryService.allCategories();
//    }
//
//    @GetMapping("/categories/{id}")
//    ResponseEntity<?> getCategoryById(@PathVariable Long id) {
//        return categoryService.findById(id);
//    }
//
//    @GetMapping("/budgets")
//    ResponseEntity<?> getAllBudgets(){
//        return new ResponseEntity<>(budgetService.allBudgets(), HttpStatus.OK);
//    }
//
//    @GetMapping("budgets/{id}")
//    ResponseEntity<?> getBudgetById(@PathVariable Long id){
//        return budgetService.findById(id);
//    }
//
//    @GetMapping("/optionBudget")
//    ResponseEntity<?> getAllOptionBudgets(){
//        return new ResponseEntity<>(optionBudgetService.allOptionBudgets(), HttpStatus.OK);
//    }
//
//    @GetMapping("/optionBudget/{id}")
//    ResponseEntity<?> getOptionBudgetById(@PathVariable Long id){
//        return optionBudgetService.findById(id);
//    }
//
//    @GetMapping("/optionComponent")
//    ResponseEntity<?> getAllOptionComponents(){
//        return new ResponseEntity<>(optionComponentService.allOptionsComponent(), HttpStatus.OK);
//    }
//    @GetMapping("/optionComponent/{id}")
//    ResponseEntity<?> getOptionComponentById(@PathVariable Long id){
//        return optionComponentService.findById(id);
//    }
//
//    @GetMapping("/clients")
//    ResponseEntity<?> getAllUsers() {
//        return clientService.allClients();
//    }
//
//    @GetMapping("/clients/id/{id}")
//    ResponseEntity<?> getClientById(@PathVariable Long id) {
//        return clientService.getClientById(id);
//    }
//
//    @GetMapping("/company/{id}")
//    ResponseEntity<?> getClientByCompany(@PathVariable Long id) {
//        return clientService.getClientsByCompany(id);
//    }
//    @DeleteMapping("/id/{id}")
//    public ResponseEntity<?> deleteClient(@PathVariable Long id, Authentication authentication) {
//        return clientService.deleteClient(id,authentication);
//    }

}
