package com.example.data.local

import com.example.data.model.MedicineDatabaseEntry

object MedicineKnowledgeBase {
    val entries: List<MedicineDatabaseEntry> = listOf(
        MedicineDatabaseEntry(
            id = "amoxicillin",
            brandName = "Amoxicillin / Augmentin",
            genericName = "Amoxicillin Trihydrate",
            category = "Antibiotic (Penicillin group)",
            standardDosageForms = "250mg, 500mg capsules / tablets / syrup",
            commonUsesEn = "Used to treat bacterial infections of ear, nose, throat, chest, urinary tract and skin.",
            commonUsesKn = "ಕಿವಿ, ಮೂಗು, ಗಂಟಲು, ಶ್ವಾಸಕೋಶ ಮತ್ತು ಚರ್ಮದ ಬ್ಯಾಕ್ಟೀರಿಯಾ ಸೋಂಕುಗಳ ನಿವಾರಣೆಗೆ ಬಳಸಲಾಗುತ್ತದೆ.",
            commonUsesHi = "कान, नाक, गले, छाती और त्वचा के जीवाणु संक्रमण के इलाज में उपयोगी।",
            directionsEn = "Take strictly as prescribed. Complete full course even if you feel better.",
            directionsKn = "ವೈದ್ಯರು ಸೂಚಿಸಿದ ಪೂರ್ಣ ಅವಧಿಗೆ ತೆಗೆದುಕೊಳ್ಳಿ. ಗುಣಮುಖವೆನಿಸಿದರೂ ಕೋರ್ಸ್ ನಿಲ್ಲಿಸಬೇಡಿ.",
            directionsHi = "डॉक्टर की सलाह अनुसार पूरा कोर्स लें। बीच में दवा बंद न करें।",
            warningsEn = "Do not take if allergic to penicillin. Consult doctor immediately if severe rash occurs.",
            warningsKn = "ಪೆನಿಸಿಲಿನ್ ಅಲರ್ಜಿ ಇರುವವರು ತೆಗೆದುಕೊಳ್ಳಬಾರದು. ಚರ್ಮದ ದದ್ದು ಬಂದರೆ ತಕ್ಷಣ ವೈದ್ಯರನ್ನು ಭೇಟಿ ಮಾಡಿ.",
            warningsHi = "पेनिसिलिन से एलर्जी होने पर न लें। दाने निकलने पर तुरंत डॉक्टर से मिलें।",
            sideEffectsEn = "Mild nausea, loose stools, headache.",
            sideEffectsKn = "ಸಾಮಾನ್ಯ ವಾಕರಿಕೆ, ಲಘು ಭೇದಿ ಅಥವಾ ತಲೆನೋವು.",
            sideEffectsHi = "हल्का जी मिचलाना, दस्त या सिरदर्द।"
        ),
        MedicineDatabaseEntry(
            id = "paracetamol",
            brandName = "Paracetamol / Dolo 650 / Calpol",
            genericName = "Acetaminophen / Paracetamol",
            category = "Antipyretic & Analgesic",
            standardDosageForms = "500mg, 650mg tablets / drops",
            commonUsesEn = "Relieves mild to moderate pain (headache, body ache) and reduces fever.",
            commonUsesKn = "ಜ್ವರ ಕಡಿಮೆ ಮಾಡಲು ಮತ್ತು ತಲೆನೋವು, ಮೈಕೈ ನೋವು ಉಪಶಮನಗೊಳಿಸಲು ಬಳಸಲಾಗುತ್ತದೆ.",
            commonUsesHi = "बुखार कम करने और सिरदर्द, बदन दर्द में राहत के लिए।",
            directionsEn = "Take after food with water. Maintain at least 4 to 6 hours between doses.",
            directionsKn = "ಊಟದ ನಂತರ ನೀರಿನೊಂದಿಗೆ ಸೇವಿಸಿ. ಎರಡು ಮಾತ್ರೆಗಳ ನಡುವೆ ಕನಿಷ್ಠ ೪-೬ ಗಂಟೆಗಳ ಅಂತರವಿರಲಿ.",
            directionsHi = "भोजन के बाद पानी के साथ लें। दो खुराकों के बीच कम से कम 4-6 घंटे का अंतर रखें।",
            warningsEn = "Never exceed 4000mg per 24 hours. Overdose can cause severe liver injury.",
            warningsKn = "ದಿನಕ್ಕೆ ಗರಿಷ್ಠ ಮಿತಿಗಿಂತ ಹೆಚ್ಚು ಸೇವಿಸಬಾರದು. ಅತಿಯಾದ ಸೇವನೆಯಿಂದ ಯಕೃತ್ತಿಗೆ (ಲಿವರ್) ಹಾನಿಯಾಗಬಹುದು.",
            warningsHi = "24 घंटे में अत्यधिक मात्रा न लें। ज्यादा दवा से लिवर को गंभीर नुकसान हो सकता है।",
            sideEffectsEn = "Rare when taken correctly. Nausea or allergic rash in sensitive people.",
            sideEffectsKn = "ಸೂಚಿಸಿದಂತೆ ತೆಗೆದುಕೊಂಡರೆ ಸುರಕ್ಷಿತ. ಅಪರೂಪವಾಗಿ ತುರಿಕೆ ಅಥವಾ ವಾಂತಿ.",
            sideEffectsHi = "सही मात्रा में सुरक्षित। कभी-कभार उल्टी या एलर्जी।"
        ),
        MedicineDatabaseEntry(
            id = "pantoprazole",
            brandName = "Pantoprazole / Pan 40 / Pantocid",
            genericName = "Pantoprazole Sodium",
            category = "Proton Pump Inhibitor (Antacid)",
            standardDosageForms = "20mg, 40mg gastro-resistant tablets",
            commonUsesEn = "Reduces excess stomach acid, relieves heartburn, acidity and gastric ulcers.",
            commonUsesKn = "ಹೊಟ್ಟೆಯಲ್ಲಿ ಆಮ್ಲದ (ಅಸಿಡಿಟಿ) ಪ್ರಮಾಣ ತಗ್ಗಿಸಲು ಮತ್ತು ಎದೆಯುರಿ, ಹುಣ್ಣುಗಳ ನಿವಾರಣೆಗೆ ಬಳಸಲಾಗುತ್ತದೆ.",
            commonUsesHi = "पेट में एसिडिटी, सीने में जलन और गैस से राहत दिलाने के लिए।",
            directionsEn = "Best taken in the morning, 30 to 60 minutes before breakfast with a glass of water.",
            directionsKn = "ಬೆಳಿಗ್ಗೆ ಉಪಹಾರಕ್ಕಿಂತ ೩೦-೬೦ ನಿಮಿಷ ಮುಂಚಿತವಾಗಿ ಖಾಲಿ ಹೊಟ್ಟೆಯಲ್ಲಿ ಸೇವಿಸುವುದು ಉತ್ತಮ.",
            directionsHi = "सुबह नाश्ते से 30 से 60 मिनट पहले खाली पेट पानी के साथ लें।",
            warningsEn = "Do not crush or chew the tablet. Swallow whole.",
            warningsKn = "ಮಾತ್ರೆಯನ್ನು ಜಗಿಯದೆ ಹಾಗೆಯೇ ನುಂಗಬೇಕು.",
            warningsHi = "गोली को चबाएं नहीं, पूरी निगलें।",
            sideEffectsEn = "Flatulence, mild diarrhea, abdominal discomfort.",
            sideEffectsKn = "ಹೊಟ್ಟೆಯುಬ್ಬರ, ಲಘು ಅತಿಸಾರ ಅಥವಾ ತಲೆಸುತ್ತು.",
            sideEffectsHi = "पेट फूलना, हल्का दस्त या बेचैनी।"
        ),
        MedicineDatabaseEntry(
            id = "metformin",
            brandName = "Metformin / Glycomet / Glucophage",
            genericName = "Metformin Hydrochloride",
            category = "Oral Antidiabetic (Biguanide)",
            standardDosageForms = "500mg, 850mg, 1000mg (SR/ER)",
            commonUsesEn = "First-line medicine to control high blood sugar in patients with Type 2 Diabetes.",
            commonUsesKn = "ಟೈಪ್ ೨ ಮಧುಮೇಹ (ಸಕ್ಕರೆ ಕಾಯಿಲೆ) ರೋಗಿಗಳಲ್ಲಿ ರಕ್ತದ ಸಕ್ಕರೆ ಪ್ರಮಾಣವನ್ನು ನಿಯಂತ್ರಿಸಲು ಬಳಸಲಾಗುತ್ತದೆ.",
            commonUsesHi = "टाइप 2 डायबिटीज में ब्लड शुगर नियंत्रित करने की प्रमुख दवा।",
            directionsEn = "Take with or immediately after meals to reduce stomach upset.",
            directionsKn = "ಹೊಟ್ಟೆ ಕೆಡದಂತೆ ತಡೆಯಲು ಊಟದೊಂದಿಗೆ ಅಥವಾ ಊಟದ ನಂತರವೇ ತೆಗೆದುಕೊಳ್ಳಿ.",
            directionsHi = "पेट की परेशानी से बचने के लिए भोजन के साथ या तुरंत बाद लें।",
            warningsEn = "Avoid excessive alcohol intake. Discontinue temporarily before contrast radiologic scans.",
            warningsKn = "ಮದ್ಯಪಾನ ಮಾಡಬಾರದು. ಕಿಡ್ನಿ ಅಥವಾ ಲಿವರ್ ತೊಂದರೆ ಇದ್ದಲ್ಲಿ ವೈದ್ಯರಿಗೆ ತಿಳಿಸಿ.",
            warningsHi = "शराब के सेवन से बचें। किडनी की जांच नियमित रूप से कराएं।",
            sideEffectsEn = "Metallic taste in mouth, bloating, loose stools in early days.",
            sideEffectsKn = "ಬಾಯಿಯಲ್ಲಿ ಲೋಹದ ರುಚಿ, ಆರಂಭದಲ್ಲಿ ಹೊಟ್ಟೆಯುಬ್ಬರ.",
            sideEffectsHi = "मुंह में धातु जैसा स्वाद, गैस या हल्का दस्त।"
        ),
        MedicineDatabaseEntry(
            id = "atorvastatin",
            brandName = "Atorvastatin / Atorva / Lipitor",
            genericName = "Atorvastatin Calcium",
            category = "Lipid-lowering Statin",
            standardDosageForms = "10mg, 20mg, 40mg tablets",
            commonUsesEn = "Lowers bad cholesterol (LDL) and triglycerides, reduces risk of heart attack & stroke.",
            commonUsesKn = "ಕೆಟ್ಟ ಕೊಲೆಸ್ಟ್ರಾಲ್ ಕಡಿಮೆ ಮಾಡಲು ಮತ್ತು ಹೃದಯಾಘಾತ ಹಾಗೂ ಪಾರ್ಶ್ವವಾಯು ಅಪಾಯ ತಪ್ಪಿಸಲು ಬಳಸಲಾಗುತ್ತದೆ.",
            commonUsesHi = "कोलेस्ट्रॉल कम करने और दिल के दौरे से बचाव के लिए।",
            directionsEn = "Usually taken once daily at bedtime, with or without food.",
            directionsKn = "ಸಾಮಾನ್ಯವಾಗಿ ರಾತ್ರಿ ಮಲಗುವ ಮುನ್ನ ದಿನಕ್ಕೊಮ್ಮೆ ತೆಗೆದುಕೊಳ್ಳಲಾಗುತ್ತದೆ.",
            directionsHi = "अमूमन रात को सोते समय दिन में एक बार ली जाती है।",
            warningsEn = "Report unexplained muscle pain or weakness to doctor immediately. Avoid grapefruit.",
            warningsKn = "ವಿವರಿಸಲಾಗದ ಸ್ನಾಯು ನೋವು ಅಥವಾ ದೌರ್ಬಲ್ಯ ಕಂಡುಬಂದರೆ ತಕ್ಷಣ ವೈದ್ಯರನ್ನು ಭೇಟಿ ಮಾಡಿ.",
            warningsHi = "अचानक मांसपेशियों में दर्द या कमजोरी होने पर तुरंत डॉक्टर को बताएं।",
            sideEffectsEn = "Joint stiffness, mild digestive upset, muscle aches.",
            sideEffectsKn = "ಕೀಲು ನೋವು ಅಥವಾ ಸ್ನಾಯುಗಳ ಬಿಗಿತ.",
            sideEffectsHi = "जोड़ों या मांसपेशियों में हल्का दर्द।"
        ),
        MedicineDatabaseEntry(
            id = "losartan",
            brandName = "Losartan / Losacar / Cozaar",
            genericName = "Losartan Potassium",
            category = "Antihypertensive (ARB)",
            standardDosageForms = "25mg, 50mg, 100mg tablets",
            commonUsesEn = "Lowers high blood pressure and protects kidneys in diabetic hypertension.",
            commonUsesKn = "ಅಧಿಕ ರಕ್ತದೊತ್ತಡ (ಬಿ.ಪಿ) ನಿಯಂತ್ರಿಸಲು ಮತ್ತು ಮೂತ್ರಪಿಂಡಗಳನ್ನು ರಕ್ಷಿಸಲು ಬಳಸಲಾಗುತ್ತದೆ.",
            commonUsesHi = "हाई ब्लड प्रेशर को नियंत्रित करने और किडनी सुरक्षा के लिए।",
            directionsEn = "Take at the same time each day, preferably morning, with plenty of water.",
            directionsKn = "ಪ್ರತಿದಿನ ಒಂದೇ ನಿಗದಿತ ಸಮಯದಲ್ಲಿ ನೀರಿನೊಂದಿಗೆ ಸೇವಿಸಿ.",
            directionsHi = "रोजाना एक ही तय समय पर पानी के साथ लें।",
            warningsEn = "Never use during pregnancy. Do not take potassium supplements without physician approval.",
            warningsKn = "ಗರ್ಭಾವಸ್ಥೆಯಲ್ಲಿ ಬಳಸಬಾರದು. ಪೊಟ್ಯಾಸಿಯಮ್ ಪೂರಕಗಳನ್ನು ವೈದ್ಯರ ಅನುಮತಿಯಿಲ್ಲದೆ ಸೇವಿಸಬೇಡಿ.",
            warningsHi = "गर्भावस्था में इसका उपयोग बिल्कुल न करें।",
            sideEffectsEn = "Dizziness upon standing up, mild fatigue, nasal congestion.",
            sideEffectsKn = "ತಲೆಸುತ್ತು, ಆಯಾಸ ಅಥವಾ ಮೂಗು ಕಟ್ಟುವಿಕೆ.",
            sideEffectsHi = "उठते समय चक्कर आना या हल्की थकान।"
        ),
        MedicineDatabaseEntry(
            id = "azithromycin",
            brandName = "Azithromycin / Azithral / Zithromax",
            genericName = "Azithromycin Dihydrate",
            category = "Macrolide Antibiotic",
            standardDosageForms = "250mg, 500mg tablets",
            commonUsesEn = "Used for respiratory infections, sinus, tonsillitis, pneumonia and throat infections.",
            commonUsesKn = "ಶ್ವಾಸಕೋಶ, ಸೈನಸ್, ಗಂಟಲು ಊತ (ಟಾನ್ಸಿಲ್) ಮತ್ತು ಉಸಿರಾಟದ ಸೋಂಕುಗಳಿಗೆ ಬಳಸಲಾಗುತ್ತದೆ.",
            commonUsesHi = "फेफड़ों, गले के टॉन्सिल, साइनस और सांस की नली के संक्रमण में उपयोगी।",
            directionsEn = "Usually taken once daily for 3 or 5 days, preferably 1 hour before or 2 hours after meals.",
            directionsKn = "ಸಾಮಾನ್ಯವಾಗಿ ೩ ಅಥವಾ ೫ ದಿನಗಳ ಕಾಲ ದಿನಕ್ಕೊಮ್ಮೆ ಆಹಾರಕ್ಕಿಂತ ಮುಂಚೆ ತೆಗೆದುಕೊಳ್ಳಬೇಕು.",
            directionsHi = "अमूमन 3 से 5 दिनों तक दिन में एक बार भोजन से एक घंटा पहले लें।",
            warningsEn = "Complete the 3-day or 5-day course as written. Inform doctor if having cardiac arrhythmia.",
            warningsKn = "ವೈದ್ಯರು ಸೂಚಿಸಿದ ಕೋರ್ಸ್ ಪೂರ್ಣಗೊಳಿಸಿ. ಹೃದಯ ಬಡಿತದ ಏರುಪೇರು ಇದ್ದರೆ ಮುನ್ಸೂಚನೆ ನೀಡಿ.",
            warningsHi = "पूरा कोर्स अवश्य पूरा करें। दिल की अनियमित धड़कन होने पर डॉक्टर को बताएं।",
            sideEffectsEn = "Stomach cramps, diarrhea, nausea.",
            sideEffectsKn = "ಹೊಟ್ಟೆ ನೋವು, ಅತಿಸಾರ ಅಥವಾ ವಾಕರಿಕೆ.",
            sideEffectsHi = "पेट में मरोड़, दस्त या उल्टी का मन होना।"
        ),
        MedicineDatabaseEntry(
            id = "cetirizine",
            brandName = "Cetirizine / Cetzine / Zyrtec",
            genericName = "Cetirizine Hydrochloride",
            category = "Second-Generation Antihistamine",
            standardDosageForms = "5mg, 10mg tablets / syrup",
            commonUsesEn = "Relieves allergy symptoms like runny nose, sneezing, itchy eyes, hives and insect bites.",
            commonUsesKn = "ನೆಗಡಿ, ಸೀನುವಿಕೆ, ಕಣ್ಣು ತುರಿಕೆ, ಚರ್ಮದ ದದ್ದು ಮತ್ತು ಅಲರ್ಜಿ ಲಕ್ಷಣಗಳನ್ನು ಶಮನಗೊಳಿಸುತ್ತದೆ.",
            commonUsesHi = "सर्दी, छींक, आंखों में खुजली और त्वचा पर एलर्जी के चकत्तों से राहत के लिए।",
            directionsEn = "Best taken in the evening or at night because it can cause mild drowsiness.",
            directionsKn = "ಇದರಿಂದ ಸ್ವಲ್ಪ ನಿದ್ದೆ ಅಥವಾ ತೂಕಡಿಕೆ ಬರಬಹುದಾದ್ದರಿಂದ ರಾತ್ರಿ ತೆಗೆದುಕೊಳ್ಳುವುದು ಉತ್ತಮ.",
            directionsHi = "हल्की नींद आ सकती है, इसलिए शाम या रात को लेना बेहतर होता है।",
            warningsEn = "Avoid driving or operating heavy machinery if feeling drowsy.",
            warningsKn = "ತೂಕಡಿಕೆ ಇದ್ದಾಗ ವಾಹನ ಚಾಲನೆ ಮಾಡಬೇಡಿ.",
            warningsHi = "नींद या सुस्ती महसूस होने पर वाहन न चलाएं।",
            sideEffectsEn = "Drowsiness, dry mouth, tiredness.",
            sideEffectsKn = "ತೂಕಡಿಕೆ, ಬಾಯಿ ಒಣಗುವುದು ಅಥವಾ ಆಯಾಸ.",
            sideEffectsHi = "सुस्ती, मुंह सूखना या थकान।"
        )
    )

    fun findByName(name: String): MedicineDatabaseEntry? {
        val query = name.lowercase().trim()
        return entries.find {
            it.brandName.lowercase().contains(query) ||
            it.genericName.lowercase().contains(query) ||
            query.contains(it.genericName.lowercase()) ||
            query.contains(it.id)
        }
    }
}
