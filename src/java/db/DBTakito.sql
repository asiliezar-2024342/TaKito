Drop database if exists DBTaKito;
Create database DBTaKito;

Use DBTaKito;


Create table Sucursal(
	codigoSucursal int not null auto_increment,
    ubicacionSucursal varchar(150) not null,
    telefonoSucursal  varchar(8) not null,
    nombreSucursal varchar(200) not null,
    funcionamiento enum('Abierto','Cerrado','Suspendido') not null,
    estado enum ('Activo','Inactivo')  not null default "Activo",
    primary key PK_codigoSucursal (codigoSucursal)
);

Create table Usuario(
	codigoUsuario int not null auto_increment,
    correoUsuario varchar(50) not null,
    contrasenaUsuario  varchar(50) not null,
    fechaRegistro date not null,
    foto mediumblob not null,
    cargo enum ('Consumidor','Administrativo','Empleado') not null,
    estado enum('Activo','Inactivo') not null default "Activo" ,
    primary key PK_codigoUsuario (codigoUsuario)
);

Create table Combo(
	codigoCombo int not null auto_increment,
    nombreCombo varchar(100) not null,
    descripcionCombo Text ,
    precioCombo decimal(10,2) not null,
    categoria enum('Familiar','Duo','Individual','Unitario') not null,
    estado enum('Activo','Inactivo') not null default "Activo",
    foto mediumblob not null,
    primary key PK_codigoCombo (codigoCombo)
);

Create table Producto(
	codigoProducto int not null auto_increment,
    nombreProducto varchar(100) not null,
    descripcionProducto Text,
    precioUnitario decimal(10,2) not null,
    existencias int not null,
    estado enum ('Activo','Inactivo')  not null default"Activo",
    primary key PK_codigoProducto (codigoProducto)
);

Create table Promocion(
	codigoPromocion int not null auto_increment,
    nombrePromocion varchar(100) not null,
    descripcionPromocion text,
    descuentoPromocion decimal(10,2) not null,
    fechaInicio Date not null,
    fechaFin Date not null,
    estado enum('Activo','Inactivo') not null default "Activo",
    primary key PK_codigoPromocion (codigoPromocion)
);

Create table Resena(
	codigoResena int not null auto_increment,
    tipo enum('Sucursal','Empleado','Producto') not null,
    tituloResena varchar(100) not null,
    comentarioResena Text,
    calificacionResena int not null,
    estado enum('Activo','Inactivo') not null default "Activo",
    codigoSucursal int not null,
    codigoUsuario int not null,
    primary key PK_codigoResena (codigoResena),
    constraint FK_Resena_Sucursal foreign key (codigoSucursal)
		references Sucursal (codigoSucursal),
	constraint FK_Resena_Usuario foreign key (codigoUsuario)
		references Usuario (codigoUsuario)
);

Create table DetalleCombo(
	codigoDetalleCombo int not null auto_increment,
    cantidad int not null,
    codigoCombo int not null,
    codigoProducto int not null,
    primary key PK_codigoDetalleCombo (codigoDetalleCombo),
    constraint FK_DetalleCombo_Combo foreign key (codigoCombo)
		references Combo (codigoCombo),
	constraint FK_DetalleCombo_Producto foreign key (codigoProducto)
		references Producto (codigoProducto)
);

Create table DetallePromocion(
	codigoDetallePromocion int not null auto_increment,
    observaciones text,
    codigoPromocion int,
    codigoCombo int not null,
    primary key PK_codigoDetallePromocion (codigoDetallePromocion),
    constraint FK_DetallePromocion_Promocion foreign key (codigoPromocion)
		references Promocion (codigoPromocion),
    constraint FK_DetallePromocion_Combo foreign key (codigoCombo)
		references Combo (codigoCombo)
);

Create table Cliente(
	codigoCliente int not null auto_increment,
    primerNombreCliente varchar(50) not null,
    segundoNombreCliente varchar(50) not null,
    primerApellidoCliente varchar(50) not null,
    segundoApellidoCliente varchar(50) not null,
    telefonoCliente varchar(8) not null,
    direccionCliente varchar(150) not null,
    sexoCliente enum("Hombre","Mujer"),
    nitCliente varchar(13) not null,
    estado enum('Activo','Inactivo') not null default "Activo",
    codigoUsuario int not null unique,
    primary key PK_codigoCliente (codigoCliente),
    constraint FK_Cliente_Usuario foreign key (codigoUsuario)
		references Usuario (codigoUsuario)
);

Create table Empleado(
	codigoEmpleado int not null auto_increment,
    primerNombreEmpleado varchar(50) not null,
    segundoNombreEmpleado varchar(50) not null,
    primerApellidoEmpleado varchar(50) not null,
    segundoApellidoEmpleado varchar(50) not null,
    telefonoEmpleado varchar(8) not null,
    correoEmpleado varchar(100) not null,
    direccionEmpleado varchar(255) not null,
    estado enum('Activo','Inactivo') not null default "Activo",
    sexoEmpleado enum("Hombre","Mujer"),
    codigoSucursal int not null,
    codigoUsuario int not null unique,
    primary key PK_codigoEmpleado (codigoEmpleado),
    constraint FK_Empleado_Sucursal foreign key (codigoSucursal)
		references Sucursal (codigoSucursal),
    constraint FK_Empleado_Usuario foreign key (codigoUsuario)
		references Usuario (codigoUsuario)
);

Create table Pedido(
	codigoPedido int not null auto_increment,
    fechaCreacion date not null,
    horaCreacion time not null,
    fechaProgramado date,
    horaProgramado time,
    ubicacionPedido varchar (255),
    tipoPedido enum('Recoger','Domicilio') not null,
    estado enum('Activo','Inactivo') not null default"Activo",
    codigoSucursal int not null,
    codigoCliente int not null,
    primary key PK_codigoPedido (codigoPedido),
    constraint FK_Pedido_Sucursal foreign key (codigoSucursal)
		references Sucursal (codigoSucursal),
    constraint FK_Pedido_Cliente foreign key (codigoCliente)
		references Cliente (codigoCliente)
);

Create table DetallePedido(
	codigoDetallePedido int not null auto_increment,
    instrucciones text,
    cantidad int,
    subTotal decimal(10,2),
    codigoPedido int not null,
    codigoCombo int not null,
    codigoPromocion int not null,
    primary key PK_codigoDetallePedido (codigoDetallePedido),
    constraint FK_DetallePedido_Pedido foreign key (codigoPedido)
		references Pedido (codigoPedido),
    constraint FK_DetallePedido_Combo foreign key (codigoCombo)
		references Combo (codigoCombo),
	constraint FK_DetallePedido_Promocion foreign key (codigoPromocion)
		references Promocion (codigoPromocion)
);

Create table Factura(
	codigoFactura int not null auto_increment,
    totalFactura decimal(10,2) not null,
    donacion decimal(10,2) not null,
    fechaFactura date not null,
    horaFactura time not null,
    metodo enum('Tarjeta','Efectivo','Transferencia') not null,
    estado enum("Activo","Inactivo")  not null default "Activo",
    codigoPedido int not null,
    codigoEmpleado int not null,
    primary key PK_codigoFactura (codigoFactura),
    constraint FK_Factura_Pedido foreign key (codigoPedido)
		references Pedido (codigoPedido),
    constraint FK_Factura_Empleado foreign key (codigoEmpleado)
		references Empleado (codigoEmpleado)
);

Create table Bitacora(
	codigoBitacora int not null auto_increment,
    mensaje text not null,
    tablaModificada varchar(100) not null,
    fecha date not null,
    hora time not null,
    datoAnterior text,
    datoNuevo text,
    accion enum('Crear', 'Actualizar', 'Eliminar'),
    codigoUsuario int not null,
    primary key PK_codigoBitacora (codigoBitacora),
    constraint FK_codigoUsuario foreign key (codigoUsuario)
		references Usuario(codigoUsuario)
);


/* Datos Ingresados a la tablas */

Insert into Sucursal (ubicacionSucursal, telefonoSucursal, nombreSucursal, funcionamiento, estado) 
values
	('Zona 10, Ciudad de Guatemala', '12345678', ' Central', 'Abierto', 'Activo'),
	('Antigua Guatemala, 3ra Calle', '87654321', 'Ta-Kito Colonial', 'Abierto', 'Activo'),
	('Quetzaltenango, Zona 1', '56789012', 'Ta-Kito Xela', 'Abierto', 'Activo'),
	('Zona 15, Ciudad de Guatemala', '23456789', 'Ta-Kito Vista Hermosa', 'Abierto', 'Activo'),
	('Escuintla, Centro Comercial', '34567890', 'Ta-Kito Escuintla', 'Abierto', 'Activo'),
	('Chimaltenango, Calle Real', '45678901', 'Ta-Kito Chimal', 'Cerrado', 'Activo'),
	('Mixco, Zona 4', '56789023', 'Ta-Kito Mixco', 'Abierto', 'Activo'),
	('Zona 1, Ciudad de Guatemala', '67890123', 'Ta-Kito Plaza Mayor', 'Abierto', 'Activo'),
	('Retalhuleu, Zona 2', '78901234', 'Ta-Kito Retalhuleu', 'Suspendido', 'Activo'),
	('Huehuetenango, Centro', '89012345', 'Ta-Kito Huehue', 'Abierto', 'Activo');

Insert into Usuario (correoUsuario, contrasenaUsuario, fechaRegistro, foto, cargo) 
values  
    ('mperez-2024357@takito.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024357.jpeg'), 'Administrativo'),
    ('jmartinez-2024083@takito.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024083.jpg'), 'Administrativo'),
    ('asiliezar-2024342@takito.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024342.jpg'), 'Administrativo'),
    ('jparedes-2024240@takito.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024240.jpg'), 'Administrativo'),
    ('wdelcid-2024243@takito.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024243.jpg'), 'Administrativo'),
    ('osicajau-2024318@takito.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024318.jpg'), 'Administrativo'),
    ('mmorales-2024279@takito.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024279.jpg'), 'Administrativo'),
    ('fpacheco-2024237@takito.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024237.jpg'), 'Administrativo'),
    ('lmontenegro-2024155@takito.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024155.jpg'), 'Administrativo'),
    ('erodriquez-2024250@takito.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024250.jpg'), 'Administrativo'),
    ('csican-2024328@takito.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024328.jpg'), 'Administrativo');
    
Insert into Usuario (correoUsuario, contrasenaUsuario, fechaRegistro, foto, cargo) 
values  
    ('mperez-2024357@gmail.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024357.jpeg'), 'Consumidor'),
    ('jmartinez-2024083@gmail.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024083.jpg'), 'Consumidor'),
    ('asiliezar-2024342@gmail.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024342.jpg'), 'Consumidor'),
    ('jparedes-2024240@gmail.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024240.jpg'), 'Consumidor'),
    ('wdelcid-2024243@gmail.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024243.jpg'), 'Consumidor'),
    ('osicajau-2024318@gmail.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024318.jpg'), 'Consumidor'),
    ('mmorales-2024279@gmail.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024279.jpg'), 'Consumidor'),
    ('fpacheco-2024237@gmail.com', '111', CURDATE(), load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024237.jpg'), 'Consumidor'),
    ('lmontenegro-2024155@gmail.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024155.jpg'), 'Consumidor'),
    ('erodriquez-2024250@gmail.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024250.jpg'), 'Consumidor'),
    ('csican-2024328@gmail.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/2024328.jpg'), 'Consumidor'),
    ('takitocorreo@takito.com', '111', CURDATE(),load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Logo.png'), 'Consumidor');
    
    
Insert into Combo (nombreCombo, descripcionCombo, precioCombo, categoria, estado, foto)
	values 
		('Combo Fiesta Familiar', 'Ideal para 4: 1 orden de Carnitas, 1 de Cochinita Pibil, 2 Burritos 1/2 LB y 4 bebidas a elección', 139.99, 'Familiar', 'Activo', load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Combo 1.png')),
		('Combo para Dos', 'Perfecto para compartir: 1 orden de Birria, 1 chilaquiles y 2 bebidas a elección', 69.99, 'Duo', 'Activo', load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Combo 2.png')),
		('Combo El Campeón', 'Para el hambre de uno: 1 chilaquiles, 1 Stacker de carne y bebida a elección', 42.99, 'Individual', 'Activo', load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Combo 3.jpg')),
		('Combo Antojo Rápido', 'Algo para el camino: 2 Stackers de carne y bebida a elección', 29.99, 'Unitario', 'Activo', load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Combo 4.png')),
		('Combo El Patrón', 'Lo mejor de la casa: 1 orden de Carnitas, 1 Burrito 1/2 LB y bebida a elección', 59.99, 'Individual', 'Activo', load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Combo 5.png'));
    
    
/* PRODUCTOS DE COMIDA */
Insert into Producto (nombreProducto, descripcionProducto, precioUnitario, existencias, estado)
	values
		('Chilaquiles', 'Totopos, salsa verde o roja, crema mexicana, queso fresco, cebolla morada, aguacate, huevos o pollo deshebrado, frijoles refritos,Con huevo, con carne, crujientes o suaves', 25.00, 50, 'Activo'),
		('Stacker de carne', 'Carne sazonada, salsa queso, mezcldescripcionProductoproductoproductoa de 3 quesos derretidos a la perfección dentro de una tortilla de harina a la plancha en forma de triángulo', 10.00, 100, 'Activo'),
		('Burrito 1/2 LB Crunchy', 'Papas crunchy, carne sazonada, crema agria, salsa queso envuelto en una tortilla de harina', 8.00, 200, 'Activo'),
		('Birria', 'Tres tacos de tortillas de maíz con costra de queso, carne de birria, cebolla, cilantro acompañado de caldo de birria', 15.00, 30, 'Inactivo'),
		('Cochinita Pibil', 'Cuatro tacos de tortilla de maíz, cochinita pibil, cebolla curtida, cilantro, cebolla y papas salteadas', 18.00, 80, 'Activo'),
		('Carnitas', 'Cuatro tacos de carnitas michoacanas, cebolla, cilantro y papas salteadas', 38.00, 50, 'Activo'),

		/* BEBIDAS */
		('Agua de Horchata', 'Refrescante agua de arroz con canela y vainilla (500ml)', 12.00, 100, 'Activo'),
		('Agua de Jamaica', 'Agua fresca de flor de jamaica (500ml)', 12.00, 100, 'Activo'),
		('Refresco de Vidrio', 'Coca-Cola, Pepsi o similar en botella de vidrio (355ml)', 10.00, 200, 'Activo');


/* PROMOCION */
Insert into Promocion (nombrePromocion, descripcionPromocion, descuentoPromocion, fechaInicio, fechaFin, estado) 
	values  ('Promo Verano', 'Descuento especial de verano', 10.00, curdate(), date_add(curdate(), interval 30 day), 'Activo'),	
			('Promo 2x1', 'Llévate dos por uno', 20.00, curdate(), date_add(curdate(), interval 15 day), 'Activo'),
            ('Fin de Semana', 'Solo sábados y domingos', 15.00, curdate(), date_add(curdate(), interval 10 day), 'Activo'),
            ('Black Friday', 'Descuentos locos', 50.00, curdate(), date_add(curdate(), interval 5 day), 'Inactivo'),
            ('Navidad', 'Promoción navideña', 30.00, curdate(), date_add(curdate(), interval 20 day), 'Activo');


/* RESENA */
Insert into Resena (tipo, tituloResena, comentarioResena, calificacionResena, estado, codigoSucursal, codigoUsuario)
	values  ('Sucursal', 'Excelente servicio', 'Muy buena atención al cliente', 5, 'Activo', 1, 1),
			('Empleado', 'Muy amable', 'El empleado fue muy atento', 4, 'Activo', 2, 2),	
            ('Producto', 'Delicioso', 'El producto superó mis expectativas', 5, 'Activo', 3, 3),	
            ('Sucursal', 'Limpieza impecable', 'El lugar estaba muy limpio', 4, 'Activo', 4, 4),	
            ('Producto', 'Calidad regular', 'Podría mejorar', 3, 'Activo', 5, 5);
Insert into Resena (tipo, tituloResena, comentarioResena, calificacionResena, estado, codigoSucursal, codigoUsuario)
	values
	('Sucursal', 'Gran experiencia en familia', 'Fuimos con la familia y el ambiente era perfecto. Limpio, espacioso y con buena música. Definitivamente volveremos.', 5, 'Activo', 1, 12),
	('Empleado', 'Atención rápida y amable', 'El mesero nos atendió super rápido a pesar de que estaba lleno. Muy recomendado para almuerzos express.', 4, 'Activo', 2, 13),
	('Producto', 'Los tacos de carnitas son increíbles', 'Probé las carnitas y estaban jugosas y bien sazonadas. La porción es generosa por el precio.', 5, 'Activo', 3, 14),
	('Sucursal', 'Ubicación conveniente pero parking limitado', 'La sucursal está bien ubicada, pero el estacionamiento es pequeño. Por lo demás, todo excelente.', 3, 'Activo', 4, 15),
	('Empleado', 'Personal capacitado y atento', 'La cajera fue muy paciente explicando el menú a mi abuela. Servicio de 10.', 5, 'Activo', 6, 17),
	('Sucursal', 'Ambiente acogedor en Antigua', 'El lugar tiene un vibe colonial que combina perfecto con los tacos. Ideal para turistas.', 4, 'Activo', 2, 18),
	('Producto', 'Chilaquiles perfectos para desayuno', 'Pedí chilaquiles con huevo y estaban crujientes pero no secos. La salsa verde es la mejor que he probado.', 5, 'Activo', 7, 19),
	('Producto', 'Burrito crunchy adictivo', 'El burrito 1/2 LB es crujiente por fuera y suave por dentro. Lo pediría de nuevo sin dudar.', 4, 'Activo', 9, 21),
	('Sucursal', 'Sucursal limpia pero ruidosa', 'Todo muy higiénico, pero el ruido del tráfico afuera molesta un poco. Sugiero mesas internas.', 4, 'Activo', 10, 22),
	('Producto', 'Cochinita pibil auténtica', 'Sabor yucateco real, con cebolla curtida perfecta. Me transportó a México.', 5, 'Activo', 1, 12),
	('Empleado', 'Excelente recomendación de combos', 'El empleado me sugirió el combo duo y fue acertado para mi pareja y yo. Gracias!', 5, 'Activo', 3, 13),
	('Producto', 'Stacker de carne simple pero bueno', 'No es nada fancy, pero por Q10 es una ganga. Buen snack rápido.', 3, 'Activo', 6, 15),
	('Sucursal', 'Decoración temática divertida', 'Me encantó el tema mexicano con colores vibrantes. Hace la experiencia más divertida.', 5, 'Activo', 8, 17),
	('Empleado', 'Servicio lento en hora pico', 'En rush hour tardaron 20 min en servir. Deben contratar más personal.', 2, 'Activo', 10, 19);


Insert into DetalleCombo (cantidad, codigoCombo, codigoProducto)
	values
		-- Combo 1: Fiesta Familiar
		(1, 1, 6), -- Carnitas
		(1, 1, 5), -- Cochinita Pibil
		(2, 1, 3), -- Burrito 1/2 LB Crunchy
		(2, 1, 7), -- Agua de Horchata
		(1, 1, 8), -- Agua de Jamaica
		(1, 1, 9), -- Refresco de Vidrio

		-- Combo 2: Para Dos
		(1, 2, 4), -- Birria
		(1, 2, 1), -- Nacho Crunchy Burrito
		(1, 2, 7), -- Agua de Horchata
		(1, 2, 8), -- Agua de Jamaica

		-- Combo 3: El Campeón
		(1, 3, 1), -- Chilaquiles
		(1, 3, 2), -- Stacker de carne
		(1, 3, 9), -- Refresco de Vidrio

		-- Combo 4: Antojo Rápido
		(2, 4, 2), -- Stacker de carne
		(1, 4, 9), -- Refresco de Vidrio

		-- Combo 5: El Patrón
		(1, 5, 6), -- Carnitas
		(1, 5, 3), -- Burrito 1/2 LB Crunchy
		(1, 5, 7); -- Agua de Horchata
            
/* DETALLE PROMOCION */
Insert into DetallePromocion (observaciones, codigoPromocion, codigoCombo)
values  ('Incluye bebida gratis', 1, 1),
		('Aplica solo los viernes', 2, 2),
        ('Descuento aplicado a Combo Familiar', 3, 1),
        ('Válido hasta agotar existencias', 4, 2),
        ('Solo válido en diciembre', 5, 1);


Insert into Cliente (
    primerNombreCliente, segundoNombreCliente, primerApellidoCliente, segundoApellidoCliente,
    telefonoCliente, direccionCliente, sexoCliente, nitCliente, estado, codigoUsuario
) values
	('Ángel', 'David', 'Siliezar', 'López', '55510001', 'Zona 1, Ciudad de Guatemala', 'Hombre', '1000000000001', 'Activo', 14),
	('Marlon', 'Eduardo', 'Pérez', 'Moreira', '55510002', 'Zona 2, Ciudad de Guatemala', 'Hombre', '1000000000002', 'Activo', 12),
	('Moisés', 'Eduardo', 'Morales', 'Alvizures', '55510003', 'Zona 3, Ciudad de Guatemala', 'Hombre', '1000000000003', 'Activo', 18),
	('Jeremy', 'Jorge', 'Aaron', 'Martínez Zamora', '55510004', 'Zona 4, Ciudad de Guatemala', 'Hombre', '1000000000004', 'Activo', 15),
	('Eduardo', 'André', 'Rodríguez', 'Ochoa', '55510005', 'Zona 5, Ciudad de Guatemala', 'Hombre', '1000000000005', 'Activo', 16),
	('Crhistian', 'Antonio', 'Sican', 'Paredes', '55510006', 'Zona 6, Ciudad de Guatemala', 'Hombre', '1000000000006', 'Activo', 17),
	('Javier', 'Eduardo', 'Paredes', 'Flores', '55510007', 'Zona 7, Ciudad de Guatemala', 'Hombre', '1000000000007', 'Activo', 13),
	('Luis', 'Eduardo', 'Montenegro', 'Rivera', '55510008', 'Zona 8, Ciudad de Guatemala', 'Hombre', '1000000000008', 'Activo', 19),
	('Oscar', 'Rodolfo', 'Abraham', 'Sicajau Mérida', '55510009', 'Zona 9, Ciudad de Guatemala', 'Hombre', '1000000000009', 'Activo', 20),
	('Fred', 'Alexandre', 'Pacheco', 'García', '55510010', 'Zona 10, Ciudad de Guatemala', 'Hombre', '1000000000010', 'Activo', 21),
	('Wilson', 'Adrián', 'Del Cid', 'Pasan', '55510011', 'Zona 11, Ciudad de Guatemala', 'Hombre', '1000000000011', 'Activo', 22),
	('TaKito', '-', '20', '25', '55510001', 'Zona 1, Ciudad de Guatemala', 'Hombre', '1000000000001', 'Activo', 23);

Insert into Empleado (
    primerNombreEmpleado, segundoNombreEmpleado, primerApellidoEmpleado, segundoApellidoEmpleado,
    telefonoEmpleado, correoEmpleado, direccionEmpleado, estado, sexoEmpleado, codigoSucursal, codigoUsuario
) values
	('Ángel', 'David', 'Siliezar', 'López', '55520001', 'angelsiliezar947@gmail.com', '15 avenida 10-25 Zona 7, Ciudad de Guatemala', 'Activo', 'Hombre', 1, 3),
	('Marlon', 'Eduardo', 'Pérez', 'Moreira', '55520002', 'marlonperez1290@gmail.com', '5 calle 12-40 Zona 1, Ciudad de Guatemala', 'Activo', 'Hombre', 2, 1),
	('Moisés', 'Eduardo', 'Morales', 'Alvizures', '55520003', 'moisesmorales582@gmail.com', '8 avenida 3-17 Zona 5, Quetzaltenango', 'Activo', 'Hombre', 3, 7),
	('Jeremy', 'Jorge', 'Aaron', 'Martínez Zamora', '55520004', 'jeremymartinez764@gmail.com', '3 calle 14-08 Zona 4, Antigua Guatemala', 'Activo', 'Hombre', 4, 2),
	('Eduardo', 'André', 'Rodríguez', 'Ochoa', '55520005', 'eduardorodriguez881@gmail.com', '2 avenida 6-22 Zona 3, Chimaltenango', 'Activo', 'Hombre', 5, 10),
	('Crhistian', 'Antonio', 'Sican', 'Paredes', '55520006', 'crhstiansican300@gmail.com', '10 calle 5-45 Zona 1, Retalhuleu', 'Activo', 'Hombre', 6, 11),
	('Javier', 'Eduardo', 'Paredes', 'Flores', '55520007', 'javierparedes479@gmail.com', '12 avenida 8-50 Zona 8, Mixco', 'Activo', 'Hombre', 7, 4),
	('Luis', 'Eduardo', 'Montenegro', 'Rivera', '55520008', 'luismontenegro102@gmail.com', '4 calle 7-14 Zona 2, Ciudad de Guatemala', 'Activo', 'Hombre', 8, 9),
	('Oscar', 'Rodolfo', 'Abraham', 'Sicajau Mérida', '55520009', 'oscarsicajau211@gmail.com', 'Avenida Central 9-33 Zona 1, Huehuetenango', 'Activo', 'Hombre', 9, 6),
	('Fred', 'Alexandre', 'Pacheco', 'García', '55520010', 'fredpacheco601@gmail.com', '7 avenida 11-21 Zona 3, Escuintla', 'Activo', 'Hombre', 10, 8),
	('Wilson', 'Adrián', 'Del Cid', 'Pasan', '55520011', 'wilsondelcid375@gmail.com', '6 calle 2-15 Zona 6, Ciudad de Guatemala', 'Activo', 'Hombre', 1, 5);


/* DETALLE PEDIDO */
Insert into Pedido (fechaCreacion, horaCreacion, fechaProgramado, horaProgramado, ubicacionPedido, tipoPedido, estado, codigoSucursal, codigoCliente)
values	(curdate(), curtime(), date_add(curdate(), interval 2 day), '14:00:00', 'Zona 1, Calle Principal', 'Domicilio', 'Activo', 1, 1),
		(curdate(), curtime(), null, null, 'Sucursal Norte', 'Recoger', 'Activo', 2, 2),
		(date_add(curdate(), interval -1 DAY), '16:30:00', date_add(curdate(), interval 1 day), '19:00:00', 'Zona 10, Avenida Secundaria', 'Domicilio', 'Activo', 3, 3),
		(curdate(), curtime(), null, null, 'Sucursal Sur', 'Recoger', 'Activo', 1, 4),
		(curdate(), '10:15:00', date_add(curdate(), interval 3 day), '13:30:00', 'Zona 5, Barrio Central', 'Domicilio', 'Activo', 2, 5);


/* DETALLE PEDIDO */
Insert into DetallePedido (instrucciones, cantidad, subTotal, codigoPedido, codigoCombo, codigoPromocion)
values	('Sin cebolla', 2, 179.98, 1, 1, 1),
		('Con extra queso', 1, 49.99, 2, 2, 2),
		('Empacar para llevar', 3, 269.97, 3, 1, 3),
		('Sin papas fritas', 1, 89.99, 4, 2, 4),
		('Entregar rápido', 2, 119.98, 5, 1, 5);


/* FACTURA  */
Insert into Factura (totalFactura, donacion, fechaFactura, horaFactura, metodo, estado, codigoPedido, codigoEmpleado)
values	(179.98, 5.00, curdate(), curtime(), 'Efectivo', 'Activo', 1, 1),
		(49.99, 2.00, curdate(), '15:30:00', 'Tarjeta', 'Activo', 2, 2),
		(269.97, 10.00, date_add(curdate(), interval -1 day), '18:45:00', 'Transferencia', 'Activo', 3, 3),
		(89.99, 0.00, curdate(), '11:00:00', 'Efectivo', 'Activo', 4, 4),
		(119.98, 3.00, curdate(), '13:15:00', 'Tarjeta', 'Activo', 5, 5);


-- Insert Bitacora entries for all 22 users
Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values

-- Administrativo users (@takito.com)
	('Usuario creado: mperez-2024357@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: mperez-2024357@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: jmartinez-2024083@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: jmartinez-2024083@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: asiliezar-2024342@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: asiliezar-2024342@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: jparedes-2024240@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: jparedes-2024240@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: wdelcid-2024243@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: wdelcid-2024243@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: osicajau-2024318@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: osicajau-2024318@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: mmorales-2024279@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: mmorales-2024279@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: fpacheco-2024237@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: fpacheco-2024237@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: lmontenegro-2024155@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: lmontenegro-2024155@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: erodriquez-2024250@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: erodriquez-2024250@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
	('Usuario creado: csican-2024328@takito.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: csican-2024328@takito.com, Cargo: Administrativo, Estado: Activo', 'Crear', 1),
-- Consumidor users (@gmail.com)
	('Usuario creado: mperez-2024357@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: mperez-2024357@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: jmartinez-2024083@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: jmartinez-2024083@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: asiliezar-2024342@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: asiliezar-2024342@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: jparedes-2024240@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: jparedes-2024240@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: wdelcid-2024243@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: wdelcid-2024243@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: osicajau-2024318@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: osicajau-2024318@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: mmorales-2024279@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: mmorales-2024279@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: fpacheco-2024237@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: fpacheco-2024237@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: lmontenegro-2024155@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: lmontenegro-2024155@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: erodriquez-2024250@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: erodriquez-2024250@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1),
	('Usuario creado: csican-2024328@gmail.com', 'Usuario', CURDATE(), CURTIME(), null, 'Correo: csican-2024328@gmail.com, Cargo: Consumidor, Estado: Activo', 'Crear', 1);

-- Bitácora de Empleados
Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Empleado registrado: Ángel David Siliezar López', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520001, Correo: angelsiliezar947@gmail.com, Dirección: 15 avenida 10-25 Zona 7, Ciudad de Guatemala', 'Crear', 1),
	('Empleado registrado: Marlon Eduardo Pérez Moreira', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520002, Correo: marlonperez1290@gmail.com, Dirección: 5 calle 12-40 Zona 1, Ciudad de Guatemala', 'Crear', 1),
	('Empleado registrado: Moisés Eduardo Morales Alvizures', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520003, Correo: moisesmorales582@gmail.com, Dirección: 8 avenida 3-17 Zona 5, Quetzaltenango', 'Crear', 1),
	('Empleado registrado: Jeremy Jorge Aaron Martínez Zamora', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520004, Correo: jeremymartinez764@gmail.com, Dirección: 3 calle 14-08 Zona 4, Antigua Guatemala', 'Crear', 1),
	('Empleado registrado: Eduardo André Rodríguez Ochoa', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520005, Correo: eduardorodriguez881@gmail.com, Dirección: 2 avenida 6-22 Zona 3, Chimaltenango', 'Crear', 1),
	('Empleado registrado: Crhistian Antonio Sican Paredes', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520006, Correo: crhstiansican300@gmail.com, Dirección: 10 calle 5-45 Zona 1, Retalhuleu', 'Crear', 1),
	('Empleado registrado: Javier Eduardo Paredes Flores', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520007, Correo: javierparedes479@gmail.com, Dirección: 12 avenida 8-50 Zona 8, Mixco', 'Crear', 1),
	('Empleado registrado: Luis Eduardo Montenegro Rivera', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520008, Correo: luismontenegro102@gmail.com, Dirección: 4 calle 7-14 Zona 2, Ciudad de Guatemala', 'Crear', 1),
	('Empleado registrado: Oscar Rodolfo Abraham Sicajau Mérida', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520009, Correo: oscarsicajau211@gmail.com, Dirección: Avenida Central 9-33 Zona 1, Huehuetenango', 'Crear', 1),
	('Empleado registrado: Fred Alexandre Pacheco García', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520010, Correo: fredpacheco601@gmail.com, Dirección: 7 avenida 11-21 Zona 3, Escuintla', 'Crear', 1),
	('Empleado registrado: Wilson Adrián Del Cid Pasan', 'Empleado', CURDATE(), CURTIME(), null, 'Teléfono: 55520011, Correo: wilsondelcid375@gmail.com, Dirección: 6 calle 2-15 Zona 6, Ciudad de Guatemala', 'Crear', 1);

-- Bitácora de Clientes
Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Cliente registrado: Ángel David Siliezar López', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510001, Correo: mperez-2024357@gmail.com, Dirección: Zona 1, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Marlon Eduardo Pérez Moreira', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510002, Correo: jmartinez-2024083@gmail.com, Dirección: Zona 2, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Moisés Eduardo Morales Alvizures', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510003, Correo: asiliezar-2024342@gmail.com, Dirección: Zona 3, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Jeremy Jorge Aaron Martínez Zamora', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510004, Correo: jparedes-2024240@gmail.com, Dirección: Zona 4, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Eduardo André Rodríguez Ochoa', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510005, Correo: wdelcid-2024243@gmail.com, Dirección: Zona 5, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Crhistian Antonio Sican Paredes', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510006, Correo: osicajau-2024318@gmail.com, Dirección: Zona 6, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Javier Eduardo Paredes Flores', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510007, Correo: mmorales-2024279@gmail.com, Dirección: Zona 7, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Luis Eduardo Montenegro Rivera', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510008, Correo: fpacheco-2024237@gmail.com, Dirección: Zona 8, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Oscar Rodolfo Abraham Sicajau Mérida', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510009, Correo: lmontenegro-2024155@gmail.com, Dirección: Zona 9, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Fred Alexandre Pacheco García', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510010, Correo: erodriquez-2024250@gmail.com, Dirección: Zona 10, Ciudad de Guatemala', 'Crear', 1),
	('Cliente registrado: Wilson Adrián Del Cid Pasan', 'Cliente', CURDATE(), CURTIME(), null, 'Teléfono: 55510011, Correo: csican-2024328@gmail.com, Dirección: Zona 11, Ciudad de Guatemala', 'Crear', 1);

Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Sucursal creada: Ta-Kito Central', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Zona 10, Ciudad de Guatemala, Teléfono: 12345678, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Colonial', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Antigua Guatemala, 3ra Calle, Teléfono: 87654321, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Xela', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Quetzaltenango, Zona 1, Teléfono: 56789012, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Vista Hermosa', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Zona 15, Ciudad de Guatemala, Teléfono: 23456789, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Escuintla', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Escuintla, Centro Comercial, Teléfono: 34567890, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Chimal', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Chimaltenango, Calle Real, Teléfono: 45678901, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Mixco', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Mixco, Zona 4, Teléfono: 56789023, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Plaza Mayor', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Zona 1, Ciudad de Guatemala, Teléfono: 67890123, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Retalhuleu', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Retalhuleu, Zona 2, Teléfono: 78901234, Estado: Activo', 'Crear', 1),
	('Sucursal creada: Ta-Kito Huehue', 'Sucursal', CURDATE(), CURTIME(), null, 'Ubicación: Huehuetenango, Centro, Teléfono: 89012345, Estado: Activo', 'Crear', 1);

Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Combo creado: Combo Fiesta Familiar', 'Combo', CURDATE(), CURTIME(), null, 'Nombre: Combo Fiesta Familiar, Precio: 139.99, Categoría: Familiar, Estado: Activo', 'Crear', 1),
	('Combo creado: Combo para Dos', 'Combo', CURDATE(), CURTIME(), null, 'Nombre: Combo para Dos, Precio: 69.99, Categoría: Duo, Estado: Activo', 'Crear', 1),
	('Combo creado: Combo El Campeón', 'Combo', CURDATE(), CURTIME(), null, 'Nombre: Combo El Campeón, Precio: 42.99, Categoría: Individual, Estado: Activo', 'Crear', 1),
	('Combo creado: Combo Antojo Rápido', 'Combo', CURDATE(), CURTIME(), null, 'Nombre: Combo Antojo Rápido, Precio: 29.99, Categoría: Unitario, Estado: Activo', 'Crear', 1),
	('Combo creado: Combo El Patrón', 'Combo', CURDATE(), CURTIME(), null, 'Nombre: Combo El Patrón, Precio: 59.99, Categoría: Individual, Estado: Activo', 'Crear', 1);

Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Producto creado: Nacho Crunchy Burrito', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Nacho Crunchy Burrito, Precio: 25.00, Existencias: 50, Estado: Activo', 'Crear', 1),
	('Producto creado: Stacker de carne', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Stacker de carne, Precio: 10.00, Existencias: 100, Estado: Activo', 'Crear', 1),
	('Producto creado: Burrito 1/2 LB Crunchy', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Burrito 1/2 LB Crunchy, Precio: 8.00, Existencias: 200, Estado: Activo', 'Crear', 1),
	('Producto creado: Birria', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Birria, Precio: 15.00, Existencias: 30, Estado: Inactivo', 'Crear', 1),
	('Producto creado: Cochinita Pibil', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Cochinita Pibil, Precio: 18.00, Existencias: 80, Estado: Activo', 'Crear', 1),
	('Producto creado: Carnitas', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Carnitas, Precio: 38.00, Existencias: 50, Estado: Activo', 'Crear', 1),
	('Producto creado: Agua de Horchata', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Agua de Horchata, Precio: 12.00, Existencias: 100, Estado: Activo', 'Crear', 1),
	('Producto creado: Agua de Jamaica', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Agua de Jamaica, Precio: 12.00, Existencias: 100, Estado: Activo', 'Crear', 1),
	('Producto creado: Refresco de Vidrio', 'Producto', CURDATE(), CURTIME(), null, 'Nombre: Refresco de Vidrio, Precio: 10.00, Existencias: 200, Estado: Activo', 'Crear', 1);

Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Promoción creada: Promo Verano', 'Promocion', CURDATE(), CURTIME(), null, 'Nombre: Promo Verano, Descuento: 10.00, Fecha Inicio: CURDATE(), Fecha Fin: DATE_ADD(CURDATE(), INTERVAL 30 DAY), Estado: Activo', 'Crear', 1),
	('Promoción creada: Promo 2x1', 'Promocion', CURDATE(), CURTIME(), null, 'Nombre: Promo 2x1, Descuento: 20.00, Fecha Inicio: CURDATE(), Fecha Fin: DATE_ADD(CURDATE(), INTERVAL 15 DAY), Estado: Activo', 'Crear', 1),
	('Promoción creada: Fin de Semana', 'Promocion', CURDATE(), CURTIME(), null, 'Nombre: Fin de Semana, Descuento: 15.00, Fecha Inicio: CURDATE(), Fecha Fin: DATE_ADD(CURDATE(), INTERVAL 10 DAY), Estado: Activo', 'Crear', 1),
	('Promoción creada: Black Friday', 'Promocion', CURDATE(), CURTIME(), null, 'Nombre: Black Friday, Descuento: 50.00, Fecha Inicio: CURDATE(), Fecha Fin: DATE_ADD(CURDATE(), INTERVAL 5 DAY), Estado: Inactivo', 'Crear', 1),
	('Promoción creada: Navidad', 'Promocion', CURDATE(), CURTIME(), null, 'Nombre: Navidad, Descuento: 30.00, Fecha Inicio: CURDATE(), Fecha Fin: DATE_ADD(CURDATE(), INTERVAL 20 DAY), Estado: Activo', 'Crear', 1);

insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Reseña creada: Excelente servicio', 'Resena', CURDATE(), CURTIME(), null, 'Tipo: Sucursal, Título: Excelente servicio, Calificación: 5, Estado: Activo, Sucursal: 1, Usuario: 1', 'Crear', 1),
	('Reseña creada: Muy amable', 'Resena', CURDATE(), CURTIME(), null, 'Tipo: Empleado, Título: Muy amable, Calificación: 4, Estado: Activo, Sucursal: 2, Usuario: 2', 'Crear', 1),
	('Reseña creada: Delicioso', 'Resena', CURDATE(), CURTIME(), null, 'Tipo: Producto, Título: Delicioso, Calificación: 5, Estado: Activo, Sucursal: 3, Usuario: 3', 'Crear', 1),
	('Reseña creada: Limpieza impecable', 'Resena', CURDATE(), CURTIME(), null, 'Tipo: Sucursal, Título: Limpieza impecable, Calificación: 4, Estado: Activo, Sucursal: 4, Usuario: 4', 'Crear', 1),
	('Reseña creada: Calidad regular', 'Resena', CURDATE(), CURTIME(), null, 'Tipo: Producto, Título: Calidad regular, Calificación: 3, Estado: Activo, Sucursal: 5, Usuario: 5', 'Crear', 1);

Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 1, Producto: 6', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 1, Producto: 5', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 2, Combo: 1, Producto: 3', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 2, Combo: 1, Producto: 7', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 1, Producto: 8', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 1, Producto: 9', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 2, Producto: 4', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 2, Producto: 1', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 2, Producto: 7', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 2, Producto: 8', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 3, Producto: 1', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 3, Producto: 2', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 3, Producto: 9', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 2, Combo: 4, Producto: 2', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 4, Producto: 9', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 5, Producto: 6', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 5, Producto: 3', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 5, Producto: 7', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 2, Combo: 1, Producto: 1', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 1, Producto: 2', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 3, Combo: 2, Producto: 1', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 2, Combo: 2, Producto: 2', 'Crear', 1),
	('Detalle de combo creado', 'DetalleCombo', CURDATE(), CURTIME(), null, 'Cantidad: 1, Combo: 1, Producto: 1', 'Crear', 1);

insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Detalle de promoción creado', 'DetallePromocion', CURDATE(), CURTIME(), null, 'Observaciones: Incluye bebida gratis, Promoción: 1, Combo: 1', 'Crear', 1),
	('Detalle de promoción creado', 'DetallePromocion', CURDATE(), CURTIME(), null, 'Observaciones: Aplica solo los viernes, Promoción: 2, Combo: 2', 'Crear', 1),
	('Detalle de promoción creado', 'DetallePromocion', CURDATE(), CURTIME(), null, 'Observaciones: Descuento aplicado a Combo Familiar, Promoción: 3, Combo: 1', 'Crear', 1),
	('Detalle de promoción creado', 'DetallePromocion', CURDATE(), CURTIME(), null, 'Observaciones: Válido hasta agotar existencias, Promoción: 4, Combo: 2', 'Crear', 1),
	('Detalle de promoción creado', 'DetallePromocion', CURDATE(), CURTIME(), null, 'Observaciones: Solo válido en diciembre, Promoción: 5, Combo: 1', 'Crear', 1);

Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Pedido creado', 'Pedido', CURDATE(), CURTIME(), null, 'Ubicación: Zona 1, Calle Principal, Tipo: Domicilio, Estado: Activo, Sucursal: 1, Cliente: 1', 'Crear', 1),
	('Pedido creado', 'Pedido', CURDATE(), CURTIME(), null, 'Ubicación: Sucursal Norte, Tipo: Recoger, Estado: Activo, Sucursal: 2, Cliente: 2', 'Crear', 1),
	('Pedido creado', 'Pedido', DATE_ADD(CURDATE(), interval -1 DAY), '16:30:00', null, 'Ubicación: Zona 10, Avenida Secundaria, Tipo: Domicilio, Estado: Activo, Sucursal: 3, Cliente: 3', 'Crear', 1),
	('Pedido creado', 'Pedido', CURDATE(), CURTIME(), null, 'Ubicación: Sucursal Sur, Tipo: Recoger, Estado: Activo, Sucursal: 1, Cliente: 4', 'Crear', 1),
	('Pedido creado', 'Pedido', CURDATE(), '10:15:00', null, 'Ubicación: Zona 5, Barrio Central, Tipo: Domicilio, Estado: Activo, Sucursal: 2, Cliente: 5', 'Crear', 1);

Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Detalle de pedido creado', 'DetallePedido', CURDATE(), CURTIME(), null, 'Instrucciones: Sin cebolla, Cantidad: 2, Subtotal: 179.98, Pedido: 1, Combo: 1, Promoción: 1', 'Crear', 1),
	('Detalle de pedido creado', 'DetallePedido', CURDATE(), CURTIME(), null, 'Instrucciones: Con extra queso, Cantidad: 1, Subtotal: 49.99, Pedido: 2, Combo: 2, Promoción: 2', 'Crear', 1),
	('Detalle de pedido creado', 'DetallePedido', DATE_ADD(CURDATE(), interval -1 day), '16:30:00', null, 'Instrucciones: Empacar para llevar, Cantidad: 3, Subtotal: 269.97, Pedido: 3, Combo: 1, Promoción: 3', 'Crear', 1),
	('Detalle de pedido creado', 'DetallePedido', CURDATE(), CURTIME(), null, 'Instrucciones: Sin papas fritas, Cantidad: 1, Subtotal: 89.99, Pedido: 4, Combo: 2, Promoción: 4', 'Crear', 1),
	('Detalle de pedido creado', 'DetallePedido', CURDATE(), '10:15:00', null, 'Instrucciones: Entregar rápido, Cantidad: 2, Subtotal: 119.98, Pedido: 5, Combo: 1, Promoción: 5', 'Crear', 1);
    
Insert into Bitacora (
    mensaje,
    tablaModificada,
    fecha,
    hora,
    datoAnterior,
    datoNuevo,
    accion,
    codigoUsuario
) values
	('Factura creada', 'Factura', CURDATE(), CURTIME(), null, 'Total: 179.98, Donación: 5.00, Método: Efectivo, Estado: Activo, Pedido: 1, Empleado: 1', 'Crear', 1),
	('Factura creada', 'Factura', CURDATE(), '15:30:00', null, 'Total: 49.99, Donación: 2.00, Método: Tarjeta, Estado: Activo, Pedido: 2, Empleado: 2', 'Crear', 1),
	('Factura creada', 'Factura', DATE_ADD(CURDATE(), interval -1 day), '18:45:00', null, 'Total: 269.97, Donación: 10.00, Método: Transferencia, Estado: Activo, Pedido: 3, Empleado: 3', 'Crear', 1),
	('Factura creada', 'Factura', CURDATE(), '11:00:00', null, 'Total: 89.99, Donación: 0.00, Método: Efectivo, Estado: Activo, Pedido: 4, Empleado: 4', 'Crear', 1),
	('Factura creada', 'Factura', CURDATE(), '13:15:00', null, 'Total: 119.98, Donación: 3.00, Método: Tarjeta, Estado: Activo, Pedido: 5, Empleado: 5', 'Crear', 1);
        
select * from Sucursal;
select * from Usuario;
select * from  Combo;
select * from Producto;
select * from Promocion;
select * from Resena;
select * from DetalleCombo;
select * from DetallePromocion;
select * from Cliente;
select * from Empleado;
select * from Pedido;
select * from DetallePedido;
select * from Factura;
select * from Bitacora;