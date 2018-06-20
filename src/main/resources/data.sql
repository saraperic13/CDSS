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
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (1, 'Curenje iz nosa', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (2, 'Bol u grlu', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (3, 'Glavobolja', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (4, 'Kijanje', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (5, 'Kašalj', TRUE );
INSERT INTO public.symptoms (symptom_id, name, value, active)
VALUES (6, 'Temperatura veća od', 38, TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (7, 'Drhtavica', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (8, 'Gubitak apetita', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (9, 'Umor', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (10, 'Žuti sekret iz nosa', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (11, 'Oticanje oko očiju', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (12, '*U kartonu (sitemu) treba da stoji da je pacijent bolovao od prehlade ili
groznice u poslednjih 60 dana', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (13, '*U poslednjih 6 meseci zabeleženo barem 10 slučajeva u kojem je pacijent imao visok pritisak.', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (14, 'Često uriniranje', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (15, 'Gubitak telesne težine', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (16, 'Zamor', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (17, 'Mučnina i povraćanje', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (18, 'Nocturia', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (20, 'Otoci nogu i zglobova', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (21, 'Gušenje', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (22, 'Bol u grudima', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (23, '*Pacijent boluje od hipertenzije više od 6 meseci', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (24, '*Pacijent boluje od dijabetesa', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (25, '*Oporavlja se od operacije', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (26, 'Dijareja', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (27, '*U poslednjih 14 dana dijagnostikovana bolest koja kao simptom ima povišenu telesnu
temperaturu', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (28, '*U poslednjih 21 dana dijagnostikovana bolest za koju je primao antibiotike', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (29, 'Bol koji se širi do ušiju', TRUE );
INSERT INTO public.symptoms (symptom_id, name, active)
VALUES (30, 'Temperatura od 40 do 41', TRUE );

-- DISEASES
INSERT INTO public.diseases (disease_id, name, disease_type, active)
VALUES (1, 'Prehlada', 0, TRUE );
INSERT INTO public.diseases (disease_id, name, disease_type, active)
VALUES (2, 'Groznica', 0, TRUE );
INSERT INTO public.diseases (disease_id, name, disease_type, active)
VALUES (3, 'Upala krajnika', 0, TRUE );
INSERT INTO public.diseases (disease_id, name, disease_type, active)
VALUES (4, 'Sinusna infekcija', 0, TRUE );
INSERT INTO public.diseases (disease_id, name, disease_type, active)
VALUES (5, 'Hipertenzija', 1, TRUE );
INSERT INTO public.diseases (disease_id, name, disease_type, active)
VALUES (6, 'Dijabetes', 1, TRUE );
INSERT INTO public.diseases (disease_id, name, disease_type, active)
VALUES (7, 'Hronična bubrežna bolest', 2, TRUE );
INSERT INTO public.diseases (disease_id, name, disease_type, active)
VALUES (8, 'Akutna bubrežna povreda', 2, TRUE );

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

-- INGREDIENTS

INSERT INTO public.ingredients (ingredient_id, name)
VALUES (1, 'Penicilin');
INSERT INTO public.ingredients (ingredient_id, name)
VALUES (2, 'Acetilinska kiselina');
INSERT INTO public.ingredients (ingredient_id, name)
VALUES (3, 'Paracetamol');

-- MEDICINES

INSERT INTO public.medicines (medicine_id, name, type)
VALUES (1, 'Fervex', 2);
INSERT INTO public.medicines (medicine_id, name, type)
VALUES (2, 'Brufen', 1);
INSERT INTO public.medicines (medicine_id, name, type)
VALUES (3, 'Aspirin', 1);
INSERT INTO public.medicines (medicine_id, name, type)
VALUES (4, 'Penicilin', 0);

-- MEDICINE INGREDIENTS

INSERT INTO public.medicine_ingredients(medicine_id, ingredient_id)
VALUES (1, 3);
INSERT INTO public.medicine_ingredients(medicine_id, ingredient_id)
VALUES (2, 2);
INSERT INTO public.medicine_ingredients(medicine_id, ingredient_id)
VALUES (3, 2);
INSERT INTO public.medicine_ingredients(medicine_id, ingredient_id)
VALUES (4, 1);