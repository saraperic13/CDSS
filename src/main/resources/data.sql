--ROLE
INSERT INTO public.role (role_id, role_type)
VALUES (1, 1);
INSERT INTO public.role (role_id, role_type)
VALUES (2, 0);

--PERMISSION
INSERT INTO public.permission (permission_id, name)
VALUES (1, 'crudDoctor');
INSERT INTO public.permission (permission_id, name)
VALUES (2, 'crudMedicine');
INSERT INTO public.permission (permission_id, name)
VALUES (3, 'readMedicine');
INSERT INTO public.permission (permission_id, name)
VALUES (4, 'crudMedicalChart');
INSERT INTO public.permission (permission_id, name)
VALUES (5, 'diagnose');
INSERT INTO public.permission (permission_id, name)
VALUES (7, 'readDiseases');
INSERT INTO public.permission (permission_id, name)
VALUES (8, 'crudDiseases');
INSERT INTO public.permission (permission_id, name)
VALUES (9, 'readDrug');
INSERT INTO public.permission (permission_id, name)
VALUES (10, 'crudDrug');

--ROLE_PERMISSIONS
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (1, 1);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (1, 2);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (2, 3);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (2, 4);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (2, 5);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (2, 7);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (1, 7);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (1, 8);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (1, 9);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (2, 9);
INSERT INTO public.role_permissions (role_id, permission_id)
VALUES (1, 10);

-- SYMPTOMS
INSERT INTO public.symptoms (symptom_id, name)
VALUES (1, 'Curenje iz nosa');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (2, 'Bol u grlu');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (3, 'Glavobolja');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (4, 'Kijanje');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (5, 'Kašalj');
INSERT INTO public.symptoms (symptom_id, name, value)
VALUES (6, 'Temperatura veća od', 48);
INSERT INTO public.symptoms (symptom_id, name)
VALUES (7, 'Drhtavica');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (8, 'Gubitak apetita');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (9, 'Umor');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (10, 'Žuti sekret iz nosa');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (11, 'Oticanje oko očiju');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (12, '*U kartonu (sitemu) treba da stoji da je pacijent bolovao od prehlade ili
groznice u poslednjih 60 dana');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (13, '*U poslednjih 6 meseci zabeleženo barem 10 slučajeva u kojem je pacijent imao visok pritisak.');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (14, 'Često uriniranje');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (15, 'Gubitak telesne težine');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (16, 'Zamor');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (17, 'Mučnina i povraćanje');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (18, 'Nocturia');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (20, 'Otoci nogu i zglobova');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (21, 'Gušenje');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (22, 'Bol u grudima');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (23, '*Pacijent boluje od hipertenzije više od 6 meseci');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (24, '*Pacijent boluje od dijabetesa');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (25, '*Oporavlja se od operacije');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (26, 'Dijareja');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (27, '*U poslednjih 14 dana dijagnostikovana bolest koja kao simptom ima povišenu telesnu
temperaturu');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (28, '*U poslednjih 21 dana dijagnostikovana bolest za koju je primao antibiotike');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (29, 'Bol koji se širi do ušiju');
INSERT INTO public.symptoms (symptom_id, name)
VALUES (30, 'Temperatura od 40 do 41');

-- DISEASES
INSERT INTO public.diseases (disease_id, name, disease_type)
VALUES (1, 'Prehlada', 0);
INSERT INTO public.diseases (disease_id, name, disease_type)
VALUES (2, 'Groznica', 0);
INSERT INTO public.diseases (disease_id, name, disease_type)
VALUES (3, 'Upala krajnika', 0);
INSERT INTO public.diseases (disease_id, name, disease_type)
VALUES (4, 'Sinusna infekcija', 0);
INSERT INTO public.diseases (disease_id, name, disease_type)
VALUES (5, 'Hipertenzija', 1);
INSERT INTO public.diseases (disease_id, name, disease_type)
VALUES (6, 'Dijabetes', 1);
INSERT INTO public.diseases (disease_id, name, disease_type)
VALUES (7, 'Hronična bubrežna bolest', 2);
INSERT INTO public.diseases (disease_id, name, disease_type)
VALUES (8, 'Akutna bubrežna povreda', 2);

--DISEASES _ SYMPTOMS

INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (1, 1);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (1, 2);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (1, 3);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (1, 4);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (1, 5);

INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (2, 4);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (2, 2);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (2, 5);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (2, 6);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (2, 1);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (2, 3);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (2, 7);

INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (3, 2);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (3, 29);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (3, 3);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (3, 30);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (3, 7);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (3, 8);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (3, 9);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (3, 10);

INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (4, 11);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (4, 3);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (4, 10);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (4, 2);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (4, 6);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (4, 5);
INSERT INTO public.disease_specific_symptoms (disease_id, symptom_id)
VALUES (4, 12);

INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (5, 13);

INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (6, 14);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (6, 15);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (6, 16);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (6, 17);

INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (7, 16);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (7, 18);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (7, 20);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (7, 21);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (7, 22);
INSERT INTO public.disease_specific_symptoms (disease_id, symptom_id)
VALUES (7, 23);
INSERT INTO public.disease_specific_symptoms (disease_id, symptom_id)
VALUES (7, 24);

INSERT INTO public.disease_specific_symptoms (disease_id, symptom_id)
VALUES (8, 25);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (8, 16);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (8, 21);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (8, 20);
INSERT INTO public.disease_common_symptoms (disease_id, symptom_id)
VALUES (8, 26);
INSERT INTO public.disease_specific_symptoms (disease_id, symptom_id)
VALUES (8, 27);
INSERT INTO public.disease_specific_symptoms (disease_id, symptom_id)
VALUES (8, 28);


