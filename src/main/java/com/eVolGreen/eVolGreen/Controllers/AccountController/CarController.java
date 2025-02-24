package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.CarDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.FlotaDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.NewCarDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.Account.Car.Flota;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Repositories.CarRepository;
import com.eVolGreen.eVolGreen.Repositories.DeviceIdentifierRepository;
import com.eVolGreen.eVolGreen.Repositories.FlotaRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FlotaService flotaService;

    @Autowired
    private FlotaRepository flotaRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DeviceIdentifierRepository deviceIdentifierRepository;

    @GetMapping("/cars")
    public List<CarDTO> getCars() {
        return carService.getCarsDTO();
    }

    @GetMapping("/cars/{id}")
    public CarDTO getCarDTO(@PathVariable Long id) {
        return carService.getCarDTO(id);
    }

    @GetMapping("/accounts/current/cars")
    public ResponseEntity<List<CarDTO>> getCars(Authentication authentication) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();
        Empresa empresa = account.getEmpresa();
        if (empresa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carService.getCarsDTOByEmpresa(empresa), HttpStatus.OK);
    }

    @GetMapping("/accounts/current/cars/{id}")
    public ResponseEntity<Object> getCarById(Authentication authentication, @PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("La cuenta no se encontró", HttpStatus.NOT_FOUND);
        }

        Car car = carService.findById(id);
        if (car == null) {
            return new ResponseEntity<>("El auto no se encontró", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/accounts/current/cars")
    public ResponseEntity<Object> createCar(Authentication authentication,
                                            @RequestBody NewCarDTO carDTO) {

        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("La cuenta no se encontró", HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();

        Empresa empresa = account.getEmpresa();
        if (empresa == null) {
            return new ResponseEntity<>("La cuenta no está asociada a ninguna empresa", HttpStatus.NOT_FOUND);
        }

        String mensaje = "";

        if (carDTO.getPatente() == null) {
            mensaje = "La patente es necesaria";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
//        if (carDTO.getModelo() == null) {
//            mensaje = "El modelo es necesario";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
        if (carDTO.getVin() == null) {
            mensaje = "El VIN es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
//        if (carDTO.getColor() == null) {
//            mensaje = "El color del auto es necesario";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (carDTO.getMarca() == null) {
//            mensaje = "La marca es necesaria en el auto";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (carDTO.getAnoFabricacion() == null) {
//            mensaje = "El año de fabricación es necesario";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (carDTO.getCapacidadPotencia() == null) {
//            mensaje = "La capacidad de potencia máxima del auto es necesaria";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }

        Car nuevoAuto = new Car(
                carDTO.getPatente(),
                carDTO.getModelo(),
                carDTO.getVin(),
                carDTO.getColor(),
                carDTO.getMarca(),
                carDTO.getAnoFabricacion(),
                carDTO.getCapacidadPotencia(),
                empresa,  
                true,
                carDTO.getAlias(),
                null
        );
      
        carService.saveCar(nuevoAuto);

        String descripcion = "Usuario " + account.getEmail() + " creó un auto de patente: " + carDTO.getPatente();
        auditLogService.recordAction(descripcion, account);


        mensaje = "El auto se ha creado con éxito";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/accounts/current/cars/{id}")
    public ResponseEntity<Object> updateCar(Authentication authentication,
                                            @PathVariable Long id,
                                            @RequestBody NewCarDTO carDTO) {

        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("La cuenta no se encontró", HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();

        Car existingCar = carService.findById(id);
        if (existingCar == null) {
            return new ResponseEntity<>("El auto no se encontró", HttpStatus.NOT_FOUND);
        }

        if (carDTO.getPatente() == null) {
            return new ResponseEntity<>("La patente es necesaria", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getModelo() == null) {
            return new ResponseEntity<>("El modelo es necesario", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getVin() == null) {
            return new ResponseEntity<>("El VIN es necesario", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getColor() == null) {
            return new ResponseEntity<>("El color del auto es necesario", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getMarca() == null) {
            return new ResponseEntity<>("La marca es necesaria en el auto", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getAnoFabricacion() == null) {
            return new ResponseEntity<>("El año de fabricación es necesario", HttpStatus.FORBIDDEN);
        }
        if (carDTO.getCapacidadPotencia() == null) {
            return new ResponseEntity<>("La capacidad de potencia máxima del auto es necesaria", HttpStatus.FORBIDDEN);
        }

        existingCar.setPatente(carDTO.getPatente());
        existingCar.setModelo(carDTO.getModelo());
        existingCar.setVin(carDTO.getVin());
        existingCar.setColor(carDTO.getColor());
        existingCar.setMarca(carDTO.getMarca());
        existingCar.setanoFabricacion(carDTO.getAnoFabricacion());
        existingCar.setCapacidadPotencia(carDTO.getCapacidadPotencia());

        carService.saveCar(existingCar);

        String descripcion = "Usuario " + account.getEmail() + " modificó un auto de patente: " + carDTO.getPatente();
        auditLogService.recordAction(descripcion, account);

        return new ResponseEntity<>("El auto se ha actualizado con éxito", HttpStatus.OK);
    }

    @PatchMapping("/accounts/current/cars/{id}/delete")
    public ResponseEntity<Object> deleteCar(Authentication authentication, @PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("La cuenta no se encontró", HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();

        Car car = carService.findById(id);
        if (car == null) {
            return new ResponseEntity<>("El auto no se encontró", HttpStatus.NOT_FOUND);
        }

        if (!car.getActivo()) {
            return new ResponseEntity<>("El auto ya está desactivado", HttpStatus.BAD_REQUEST);
        }

        // Desactivar el auto
        car.setActivo(false);
        carService.saveCar(car);

        String descripcion = "Usuario " + account.getEmail() + " eliminó un auto de patente: " + car.getPatente();
        auditLogService.recordAction(descripcion, account);

        // Desactivar los dispositivos de identificación asociados al auto
        List<DeviceIdentifier> deviceIdentifiers = deviceIdentifierRepository.findByAuto(car);
        for (DeviceIdentifier deviceIdentifier : deviceIdentifiers) {
            if (deviceIdentifier.getActivo()) {
                deviceIdentifier.setActivo(false);
                deviceIdentifierRepository.save(deviceIdentifier);
            }
        }

        return ResponseEntity.ok("El auto y sus dispositivos de identificación han sido desactivados correctamente.");
    }

    @GetMapping("/cars/template")
    public ResponseEntity<byte[]> downloadCarTemplate() throws IOException {
        // Crear el archivo Excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Plantilla de Autos");
        Row headerRow = sheet.createRow(0);

        // Agregar los headers, incluyendo la nueva columna "Flota"
        String[] headers = {"Patente", "VIN", "Modelo", "Alias", "Flota"}; // Se agregó "Flota"
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Ajustar automáticamente el tamaño de las columnas
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribir los datos a un array de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Configurar las cabeceras para la descarga
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headersResponse.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=plantilla_autos.xlsx");

        return ResponseEntity.ok()
                .headers(headersResponse)
                .body(outputStream.toByteArray());
    }

    @PostMapping("/cars/upload")
    public ResponseEntity<String> uploadCars(@RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
        Optional<Account> accountOpt = accountService.findByEmail(authentication.getName());
        if (accountOpt.isEmpty()) {
            return new ResponseEntity<>("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
        Account account = accountOpt.get();

        // Obtener la empresa del usuario
        Empresa empresa = account.getEmpresa();
        if (empresa == null) {
            return new ResponseEntity<>("La cuenta no está asociada a ninguna empresa", HttpStatus.FORBIDDEN);
        }
        List<Car> cars = new ArrayList<>();

        // Leer el archivo Excel
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // Iterar sobre las filas (comenzando en 1 para evitar los headers)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            // Leer las celdas
            String patente = getCellAsString(row.getCell(0));
            String vin = getCellAsString(row.getCell(1));
            String modelo = getCellAsString(row.getCell(2));
            String alias = getCellAsString(row.getCell(3));
            String nombreFlota = getCellAsString(row.getCell(4)); // Nueva columna "Flota"

            // Buscar la flota por nombre
            Flota flota = null;
            if (nombreFlota != null && !nombreFlota.isEmpty()) {
                flota = flotaService.findByNombreFlotaAndEmpresa(nombreFlota, empresa);
                if (flota == null) {
                    return ResponseEntity.badRequest().body("Error en la fila " + (i + 1) + ": La flota '" + nombreFlota + "' no existe.");
                }
            }

            // Crear una instancia de Car y agregarla a la lista
            Car car = new Car();
            car.setPatente(patente);
            car.setVin(vin);
            car.setModelo(modelo);
            car.setAlias(alias);
            car.setEmpresa(empresa);
            car.setActivo(true);
            if (flota != null) {
                car.setFlota(flota);
            }
            cars.add(car);
        }

        // Guardar los autos en la base de datos
        for (Car car : cars) {
            carService.saveCar(car);
        }

        return ResponseEntity.ok("Autos subidos correctamente");
    }

    private String getCellAsString(Cell cell) {
        if (cell == null) {
            return ""; // Retorna cadena vacía si la celda está vacía
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Si es numérica, la convertimos a cadena
                return String.valueOf((long) cell.getNumericCellValue()); // Convertimos a entero si es necesario
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return ""; // Otros tipos de celdas se ignoran
        }
    }

    @GetMapping("/flotas")
    public ResponseEntity<List<FlotaDTO>> getFlotas(Authentication authentication) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Account account = accountOptional.get();
        Empresa empresa = account.getEmpresa();
        if (empresa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<FlotaDTO> flotasDTO = flotaService.getFlotasDTOByEmpresa(empresa);
        return new ResponseEntity<>(flotasDTO, HttpStatus.OK);
    }

    @GetMapping("/flotas/{id}")
    public ResponseEntity<FlotaDTO> getFlotaById(@PathVariable Long id) {
        return flotaRepository.findById(id)
                .map(flota -> new ResponseEntity<>(new FlotaDTO(flota), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("flotas/{id}")
    public ResponseEntity<String> updateFlota(@PathVariable Long id, @RequestBody Flota flotaDetails) {
        return flotaRepository.findById(id)
                .map(flota -> {
                    flota.setNombreFlota(flotaDetails.getNombreFlota());
                    flota.setPrecioFlota(flotaDetails.getPrecioFlota());
                    flotaRepository.save(flota);
                    return new ResponseEntity<>("Flota actualizada exitosamente", HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>("Flota no encontrada", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create-flota")
    public ResponseEntity<String> createFlota(@RequestBody FlotaDTO flotaDTO, Authentication authentication) {
        // Obtiene la cuenta del usuario autenticado
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }

        Account account = accountOptional.get();
        Empresa empresa = account.getEmpresa();

        if (empresa == null) {
            return new ResponseEntity<>("Empresa no asociada a la cuenta", HttpStatus.NOT_FOUND);
        }

        // Crear una nueva flota y asignarle la empresa del usuario
        Flota nuevaFlota = new Flota();
        nuevaFlota.setNombreFlota(flotaDTO.getNombreFlota());
        nuevaFlota.setPrecioFlota(flotaDTO.getPrecioFlota());
        nuevaFlota.setEmpresa(empresa);
        nuevaFlota.setActivo(true); // Asigna el valor activo como true por defecto

        flotaService.saveFlota(nuevaFlota);
        return new ResponseEntity<>("Flota creada exitosamente", HttpStatus.CREATED);
    }
    @PostMapping("flotas/{flotaId}/assign-cars")
    public ResponseEntity<String> assignCarsToFlota(@PathVariable Long flotaId, @RequestBody List<Long> carIds) {
        Flota flota = flotaService.findById(flotaId);
        if (flota == null) {
            return new ResponseEntity<>("Flota no encontrada", HttpStatus.NOT_FOUND);
        }

        // Asignar cada auto a la flota
        for (Long carId : carIds) {
            Car car = carService.findById(carId);
            if (car != null) {
                car.setFlota(flota);
                carService.saveCar(car);
            }
        }

        return new ResponseEntity<>("Autos asignados a la flota exitosamente", HttpStatus.OK);
    }

    @PatchMapping("/flotas/{id}/delete")
    public ResponseEntity<Object> deleteFlota(Authentication authentication, @PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());
        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>("La cuenta no se encontró", HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();

        Optional<Flota> flotaOptional = flotaRepository.findById(id);
        if (flotaOptional.isEmpty()) {
            return new ResponseEntity<>("La flota no se encontró", HttpStatus.NOT_FOUND);
        }

        Flota flota = flotaOptional.get();

        if (!flota.getActivo()) {
            return new ResponseEntity<>("La flota ya está desactivada", HttpStatus.BAD_REQUEST);
        }

        // Desactivar la flota
        flota.setActivo(false);
        flotaRepository.save(flota);

        // Liberar los autos asociados a la flota
        List<Car> cars = carRepository.findByFlota(flota);
        for (Car car : cars) {
            car.setFlota(null);
            carRepository.save(car);
        }

        // Registrar la acción en los logs
        String descripcion = "Usuario " + account.getEmail() + " eliminó la flota: " + flota.getNombreFlota() + " y liberó los autos asociados";
        auditLogService.recordAction(descripcion, account);

        return ResponseEntity.ok("La flota ha sido desactivada y los autos asociados han sido liberados correctamente.");
    }



//
//    @PostMapping("/clients/current/car")
//    public ResponseEntity<Object> createCar(Authentication authentication,
//                                            @RequestBody Car newCar,
//                                            @RequestParam String numberAccount) {
//
//        Client clientCurrent = clientUserService.findByEmail(authentication.getName());
//        String message = "";
//        try {
//            // Validate parameters
//            if (newCar.getLicensePlate().isBlank()){
//                message = "Missing license plate";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getModel().isBlank()){
//                message = "Missing model";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getVin().isBlank()){
//                message = "Missing vin";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getColor().isBlank()){
//                message = "Missing color";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getBrand().isBlank()){
//                message = "Missing make";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getManufactureYear().isBlank()){
//                message = "Missing year";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (newCar.getCapacityFullPower() == null){
//                message = "Missing mileage";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//            if (numberAccount.isBlank()){
//                message = "Missing account number";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//
//            // Find the account with the provided account number
//            Optional<Account> optionalAccount = clientCurrent.getAccounts().stream()
//                    .filter(account -> account.getNumber().equals(numberAccount))
//                    .findFirst();
//
//            // Check if the account exists
//            if (optionalAccount.isEmpty()) {
//                message = "The provided account number does not belong to the current client";
//                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//            }
//
//            // Get the account
//            Account selectedAccount = optionalAccount.get();
//
//            // Create the car
//            Car car = new Car( newCar.getLicensePlate(),
//                    newCar.getModel(),
//                    newCar.getVin(),
//                    newCar.getColor(),
//                    newCar.getBrand(),
//                    newCar.getManufactureYear(),
//                    newCar.getCapacityFullPower());
//
//            // Set the car's identifier
//            String indentifierName ="CARD-" + getStringRandomIdentifier();
//            long indentifierNumber = getIndentifierNumber() ;
//            DeviceIdentifier cardIdentifier = new DeviceIdentifier(
//                    indentifierName,
//                    indentifierNumber,
//                    "Card",
//                    car);
//            car.addDeviceIdentifier(cardIdentifier);
//            cardIdentifier.setCar(car);
//
//            String body = String.valueOf(cardIdentifier);
//            // Set the car's account
//            car.setAccount(selectedAccount);
//
//            String email = clientCurrent.getEmail();
//            Email emailMessage = new Email( email,
//                        "Your Car Has Been Successfully Created!",
//                                body);
//            emailService.sendCarCreatedEmail(emailMessage);
//
//            // Save the car
//            carRepository.save(car);
//            deviceIdentifierService.saveDeviceIdentifier(cardIdentifier);
//            return new ResponseEntity<>("Car created successfully", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error creating car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    int min = 00000000;
//    int max = 99999999;
//
//    public int getRandomNumber(int min, int max) {
//        return (int) ((Math.random() * (max - min)) + min);
//    }
//
//    public String getStringRandomIdentifier() {
//        int randomNumber = getRandomNumber(min, max);
//        return String.valueOf(randomNumber);
//    }
//
//    public Long getIndentifierNumber (){
//        long randomNumber = getRandomNumber(min, max);
//        return randomNumber;
//    }
//
//    @DeleteMapping("/clients/current/car/{id}")
//    public ResponseEntity<Object> deleteCar(Authentication authentication, @PathVariable Long id) {
//        Client client = clientUserService.findByEmail(authentication.getName());
//        Car car = carService.findById(id);
//
//        if (car == null) {
//            String message = "Car not found";
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//        }
//
//        // Verificar si el carro pertenece al cliente actual
//        if (!car.getAccount().getClient().equals(client)) {
//            String message = "Unauthorized access: Car does not belong to the current client";
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
//        }
//
//        // Buscar el dispositivo identificador asociado al carro
//        DeviceIdentifier deviceIdentifier = null;
//        for (DeviceIdentifier identifier : car.getDeviceIdentifiers()) {
//            if (identifier.getCar().equals(car)) {
//                deviceIdentifier = identifier;
//                break;
//            }
//        }
//
//        if (deviceIdentifier != null) {
//            // Eliminar el dispositivo identificador asociado al carro
//            deviceIdentifierService.deleteDeviceIdentifier(deviceIdentifier.getId());
//        }
//
//        // Eliminar el carro
//        carService.deleteCar(id);
//
//        // Enviar correo electrónico de confirmación de eliminación
//        String email = client.getEmail();
//        String body = "Your car has been deleted successfully.";
//        Email emailMessage = new Email(email, "Your Car Has Been Successfully Deleted!", body);
//        emailService.sendCarDeletedEmail(emailMessage);
//
//        String message = "Car deleted successfully";
//        return ResponseEntity.ok(message);
//    }
//
//


}
