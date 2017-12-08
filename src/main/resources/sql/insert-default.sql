INSERT INTO  perfil (id_perfil, perfil) VALUES (1, 'Administrador');
INSERT INTO  perfil (id_perfil, perfil) VALUES (2, 'Director DEA');
INSERT INTO  perfil (id_perfil, perfil) VALUES (3, 'Coordinador DEA');
INSERT INTO  perfil (id_perfil, perfil) VALUES (4, 'Docente');

INSERT INTO  persona (ci, nombre, apellido, cuenta, contrasenia, estado, theme) VALUES ('555', 'Admin', 'Admin', 'admin', '202cb962ac59075b964b07152d234b70', 'ACTIVO', 'cupertino');

INSERT INTO  perfil_persona (id_perfil, ci) VALUES (1, '555');

INSERT INTO  menu (id_menu, id_perfil, menu) VALUES (1, 1, 'HOME');
INSERT INTO  menu (id_menu, id_perfil, menu) VALUES (2, 1, 'ADMINISTRAR');
INSERT INTO  menu (id_menu, id_perfil, menu) VALUES (3, 1, 'USUARIOS');
INSERT INTO  menu (id_menu, id_perfil, menu) VALUES (4, 1, 'REGISTRO MATERIA');
INSERT INTO  menu (id_menu, id_perfil, menu) VALUES (5, 1, 'ESCALAFON DOCENTE');

INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (1, 1, 'Inicio', 'welcome.xhtml', 'fa fa-home');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (2, 1, 'Cuenta', 'cuenta.xhtml', 'fa fa-user');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (3, 2, 'Universidad', 'universidad.xhtml', 'fa fa-university');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (4, 2, 'Facultad', 'facultad.xhtml', 'fa fa-institution');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (5, 2, 'Carrera', 'carrera.xhtml', 'fa fa-graduation-cap');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (6, 2, 'Materia', 'materia.xhtml', 'fa fa-book');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (7, 3, 'Usuarios', 'usuario.xhtml', 'fa fa-users');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (8, 3, 'Director DEA', 'director_dea.xhtml', 'fa fa-user-md');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (9, 3, 'Coordinador DEA', 'coordinador_dea.xhtml', 'fa fa-user-md');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (10, 3, 'Docente', 'docente.xhtml', 'fa fa-user-md');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (11, 4, 'Registro Materia', 'docenteRegistroMateria.xhtml', 'fa fa-eyedropper');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (12, 5, 'Docente Escalafon', 'docenteEscalafon.xhtml', 'fa fa-level-up');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (13, 5, 'Historial Escalafon', 'docente_cartilla.xhtml', 'fa fa-line-chart');
INSERT INTO  submenu (id_submenu, id_menu, submenu, link, icon) VALUES (14, 5, 'Plantillas', 'plantilla_resolucion.xhtml', 'fa fa-list-alt');