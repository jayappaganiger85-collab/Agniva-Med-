package com.example.data.local

import com.example.R
import com.example.data.model.ConfidenceLevel
import com.example.data.model.MedicineItem
import com.example.data.model.PrescriptionRecord
import com.example.data.model.VerificationStatus

object SamplePrescriptions {

    val sampleSharma = PrescriptionRecord(
        id = "rx_sample_sharma",
        title = "Dr. R. Sharma - General Consultation",
        doctorName = "Dr. R. Sharma, MBBS MD",
        clinicOrHospital = "Bhatia City Hospital",
        patientName = "Mr. Suresh Kumar",
        scanDate = System.currentTimeMillis() - 86400000L * 2, // 2 days ago
        imageUri = null,
        sampleDrawableResId = R.drawable.rx_sample_sharma_1794490611866,
        overallConfidence = 96,
        hasUncertainWords = false,
        generalAdviceEn = "Drink plenty of warm fluids, rest well, and finish the antibiotic course strictly.",
        generalAdviceKn = "ಧಾರಾಳವಾಗಿ ಬೆಚ್ಚಗಿನ ನೀರು ಕುಡಿಯಿರಿ, ವಿಶ್ರಾಂತಿ ಪಡೆಯಿರಿ ಮತ್ತು ಆಂಟಿಬಯೋಟಿಕ್ ಔಷಧಿಯನ್ನು ಸಂಪೂರ್ಣವಾಗಿ ಮುಗಿಸಿ.",
        generalAdviceHi = "गुनगुना पानी पिएं, पर्याप्त आराम करें और एंटीबायोटिक का कोर्स पूरा करें।",
        medicines = listOf(
            MedicineItem(
                id = "med_1",
                name = "Amoxicillin 500mg",
                genericName = "Amoxicillin Trihydrate",
                dosage = "500 mg",
                frequency = "1 - 0 - 1 (Morning & Night)",
                timing = "After food with water / ಊಟದ ನಂತರ",
                duration = "5 Days / ೫ ದಿನಗಳು",
                originalHandwrittenSnippet = "Amoxicillin 500mg , 1-0-1",
                confidenceScore = 98,
                confidenceLevel = ConfidenceLevel.HIGH,
                verificationStatus = VerificationStatus.VERIFIED_HIGH_CONFIDENCE,
                purposeSummaryEn = "Antibiotic prescribed to clear bacterial respiratory and throat infection.",
                purposeSummaryKn = "ಗಂಟಲು ಮತ್ತು ಉಸಿರಾಟದ ಬ್ಯಾಕ್ಟೀರಿಯಾ ಸೋಂಕನ್ನು ಹೋಗಲಾಡಿಸಲು ನೀಡಲಾದ ಆಂಟಿಬಯೋಟಿಕ್.",
                purposeSummaryHi = "गले और श्वसन संक्रमण के इलाज हेतु एंटीबायोटिक।",
                precautionsEn = "Do not skip doses. Complete all 5 days even if symptoms disappear.",
                precautionsKn = "ಮಾತ್ರೆ ಬಿಡಬೇಡಿ. ಗುಣವಾದಂತೆ ಕಂಡರೂ ೫ ದಿನಗಳ ಪೂರ್ಣ ಕೋರ್ಸ್ ತೆಗೆದುಕೊಳ್ಳಿ.",
                precautionsHi = "खुराक न छोड़ें। लक्षण ठीक होने पर भी पूरा कोर्स करें।",
                sideEffectsEn = "Mild loose motion, temporary nausea.",
                sideEffectsKn = "ಸಾಮಾನ್ಯ ವಾಕರಿಕೆ, ಲಘು ಭೇದಿ.",
                sideEffectsHi = "हल्का पेट खराब या जी मिचलाना।"
            ),
            MedicineItem(
                id = "med_2",
                name = "Paracetamol 650mg",
                genericName = "Paracetamol (Acetaminophen)",
                dosage = "650 mg",
                frequency = "1 - 0 - 1 (Morning & Night as needed)",
                timing = "After food / ಊಟದ ನಂತರ",
                duration = "3 Days / ೩ ದಿನಗಳು",
                originalHandwrittenSnippet = "Paracetamol 650mg , 1-0-1",
                confidenceScore = 97,
                confidenceLevel = ConfidenceLevel.HIGH,
                verificationStatus = VerificationStatus.VERIFIED_HIGH_CONFIDENCE,
                purposeSummaryEn = "Relieves body aches, fever and malaise.",
                purposeSummaryKn = "ಮೈಕೈ ನೋವು ಮತ್ತು ಜ್ವರ ಕಡಿಮೆ ಮಾಡಲು ನೀಡಲಾಗಿದೆ.",
                purposeSummaryHi = "बुखार और बदन दर्द में राहत के लिए।",
                precautionsEn = "Maintain at least 6 hours gap. Do not exceed 4 tablets in 24 hours.",
                precautionsKn = "ಕನಿಷ್ಠ ೬ ಗಂಟೆಗಳ ಅಂತರವಿರಲಿ. ದಿನಕ್ಕೆ ೪ ಕ್ಕಿಂತ ಹೆಚ್ಚು ಮಾತ್ರೆ ಸೇವಿಸಬೇಡಿ.",
                precautionsHi = "दो गोलियों के बीच 6 घंटे का अंतर रखें। 24 घंटे में 4 से ज्यादा न लें।",
                sideEffectsEn = "Generally well tolerated.",
                sideEffectsKn = "ಸೂಚಿಸಿದಂತೆ ತೆಗೆದುಕೊಂಡರೆ ಸುರಕ್ಷಿತ.",
                sideEffectsHi = "सामान्यतः सुरक्षित।"
            ),
            MedicineItem(
                id = "med_3",
                name = "Pantoprazole 40mg",
                genericName = "Pantoprazole Sodium",
                dosage = "40 mg",
                frequency = "1 - 0 - 0 (Morning Only)",
                timing = "Empty stomach before breakfast / ಬೆಳಿಗ್ಗೆ ಖಾಲಿ ಹೊಟ್ಟೆಯಲ್ಲಿ",
                duration = "5 Days / ೫ ದಿನಗಳು",
                originalHandwrittenSnippet = "Pantoprazole 40mg , 1-0-0",
                confidenceScore = 94,
                confidenceLevel = ConfidenceLevel.HIGH,
                verificationStatus = VerificationStatus.VERIFIED_HIGH_CONFIDENCE,
                purposeSummaryEn = "Protects gastric lining against acidity caused by antibiotics.",
                purposeSummaryKn = "ಆಂಟಿಬಯೋಟಿಕ್ ನಿಂದ ಉಂಟಾಗುವ ಗ್ಯಾಸ್ ಮತ್ತು ಎದೆಯುರಿ ತಡೆಯಲು ನೀಡಲಾಗಿದೆ.",
                purposeSummaryHi = "दवाइयों से होने वाली गैस और एसिडिटी से पेट की सुरक्षा के लिए।",
                precautionsEn = "Take 30 minutes before breakfast with a glass of plain water.",
                precautionsKn = "ಬೆಳಗಿನ ಉಪಹಾರಕ್ಕಿಂತ ೩೦ ನಿಮಿಷ ಮುಂಚೆ ಖಾಲಿ ಹೊಟ್ಟೆಯಲ್ಲಿ ನುಂಗಬೇಕು.",
                precautionsHi = "सुबह नाश्ते से 30 मिनट पहले खाली पेट लें।",
                sideEffectsEn = "Mild flatulence or headache.",
                sideEffectsKn = "ಹೊಟ್ಟೆಯುಬ್ಬರ ಅಥವಾ ಲಘು ತಲೆನೋವು.",
                sideEffectsHi = "हल्की गैस या सिरदर्द।"
            )
        )
    )

    val sampleRao = PrescriptionRecord(
        id = "rx_sample_rao",
        title = "Dr. Ananya Rao - Cardio & Diabetes Care",
        doctorName = "Dr. Ananya Rao, MD DM Cardiology",
        clinicOrHospital = "Apollo Hospitals",
        patientName = "Mr. Suresh Kumar",
        scanDate = System.currentTimeMillis() - 86400000L * 5, // 5 days ago
        imageUri = null,
        sampleDrawableResId = R.drawable.rx_sample_rao_1794490626707,
        overallConfidence = 95,
        hasUncertainWords = false,
        generalAdviceEn = "Maintain low sodium diet, 30 minutes daily walking, check blood pressure weekly.",
        generalAdviceKn = "ಕಡಿಮೆ ಉಪ್ಪಿನ ಆಹಾರ ಸೇವಿಸಿ, ಪ್ರತಿದಿನ ೩೦ ನಿಮಿಷ ವಾಕಿಂಗ್ ಮಾಡಿ, ವಾರಕ್ಕೊಮ್ಮೆ ಬಿ.ಪಿ ಪರಿಶೀಲಿಸಿ.",
        generalAdviceHi = "नमक कम लें, रोजाना 30 मिनट टहलें और हफ्ते में एक बार ब्लड प्रेशर मापें।",
        medicines = listOf(
            MedicineItem(
                id = "med_r1",
                name = "Metformin 500mg",
                genericName = "Metformin Hydrochloride",
                dosage = "500 mg",
                frequency = "1 tab OD (Once Daily)",
                timing = "After dinner / ರಾತ್ರಿ ಊಟದ ನಂತರ",
                duration = "Continuous / ಮುಂದುವರಿಸಿ",
                originalHandwrittenSnippet = "Metformin 500mg , 1 tab OD, after dinner",
                confidenceScore = 96,
                confidenceLevel = ConfidenceLevel.HIGH,
                verificationStatus = VerificationStatus.VERIFIED_HIGH_CONFIDENCE,
                purposeSummaryEn = "Regulates evening and fasting blood sugar levels for Type 2 Diabetes.",
                purposeSummaryKn = "ಟೈಪ್ ೨ ಮಧುಮೇಹದಲ್ಲಿ ರಕ್ತದ ಸಕ್ಕರೆ ಪ್ರಮಾಣವನ್ನು ಸಮತೋಲನದಲ್ಲಿಡುತ್ತದೆ.",
                purposeSummaryHi = "टाइप 2 शुगर के मरीजों में ब्लड शुगर नियंत्रित रखने के लिए।",
                precautionsEn = "Take during or after food to prevent stomach discomfort.",
                precautionsKn = "ಹೊಟ್ಟೆ ನೋವು ತಪ್ಪಿಸಲು ಊಟವಾದ ತಕ್ಷಣ ತೆಗೆದುಕೊಳ್ಳಿ.",
                precautionsHi = "पेट खराब होने से बचाने के लिए भोजन के तुरंत बाद लें।",
                sideEffectsEn = "Mild metallic taste or loose stool in first week.",
                sideEffectsKn = "ಬಾಯಿಯಲ್ಲಿ ಲೋಹದ ರುಚಿ ಅಥವಾ ಲಘು ಅತಿಸಾರ.",
                sideEffectsHi = "मुंह में अजीब स्वाद या हल्का दस्त।"
            ),
            MedicineItem(
                id = "med_r2",
                name = "Atorvastatin 10mg",
                genericName = "Atorvastatin Calcium",
                dosage = "10 mg",
                frequency = "1 tab OD (Once Daily)",
                timing = "At bedtime / ಮಲಗುವ ಮುನ್ನ",
                duration = "Continuous / ಮುಂದುವರಿಸಿ",
                originalHandwrittenSnippet = "Atorvastatin 10mg , 1 tab OD, at bedtime",
                confidenceScore = 95,
                confidenceLevel = ConfidenceLevel.HIGH,
                verificationStatus = VerificationStatus.VERIFIED_HIGH_CONFIDENCE,
                purposeSummaryEn = "Lowers LDL cholesterol and protects coronary blood vessels.",
                purposeSummaryKn = "ರಕ್ತನಾಳಗಳಲ್ಲಿ ಕೆಟ್ಟ ಕೊಲೆಸ್ಟ್ರಾಲ್ ಶೇಖರಣೆಯಾಗದಂತೆ ತಡೆಯುತ್ತದೆ.",
                purposeSummaryHi = "खराब कोलेस्ट्रॉल कम कर हृदय की नसों को सुरक्षित रखता है।",
                precautionsEn = "Take regularly at night. Do not consume grapefruit juice.",
                precautionsKn = "ಪ್ರತಿದಿನ ರಾತ್ರಿ ಮಲಗುವ ಮುನ್ನ ಒಂದೇ ವೇಳೆಗೆ ಸೇವಿಸಿ.",
                precautionsHi = "नियमित रूप से रात में लें।",
                sideEffectsEn = "Mild muscle stiffness or cramps.",
                sideEffectsKn = "ಸ್ನಾಯುಗಳಲ್ಲಿ ಲಘು ನೋವು.",
                sideEffectsHi = "मांसपेशियों में हल्का खिंचाव।"
            ),
            MedicineItem(
                id = "med_r3",
                name = "Losartan 25mg",
                genericName = "Losartan Potassium",
                dosage = "25 mg",
                frequency = "1 tab OD (Once Daily)",
                timing = "In morning with water / ಬೆಳಿಗ್ಗೆ ನೀರಿನೊಂದಿಗೆ",
                duration = "Continuous / ಮುಂದುವರಿಸಿ",
                originalHandwrittenSnippet = "Losartan 25mg , 1 tab OD, in morning",
                confidenceScore = 94,
                confidenceLevel = ConfidenceLevel.HIGH,
                verificationStatus = VerificationStatus.VERIFIED_HIGH_CONFIDENCE,
                purposeSummaryEn = "Controls arterial blood pressure and supports heart workload.",
                purposeSummaryKn = "ಅಧಿಕ ರಕ್ತದೊತ್ತಡವನ್ನು (ಬಿ.ಪಿ) ನಿಯಂತ್ರಣದಲ್ಲಿಡುತ್ತದೆ.",
                purposeSummaryHi = "उच्च रक्तचाप (बीपी) को नियंत्रित रखने के लिए।",
                precautionsEn = "Do not stop abruptly without consulting your cardiologist.",
                precautionsKn = "ವೈದ್ಯರ ಸಲಹೆಯಿಲ್ಲದೆ ಔಷಧಿಯನ್ನು ಏಕಾಏಕಿ ನಿಲ್ಲಿಸಬೇಡಿ.",
                precautionsHi = "डॉक्टर की सलाह के बिना अचानक दवा बंद न करें।",
                sideEffectsEn = "Slight dizziness when standing quickly.",
                sideEffectsKn = "ಬೇಗನೆ ಎದ್ದು ನಿಂತಾಗ ಲಘು ತಲೆಸುತ್ತು.",
                sideEffectsHi = "अचानक उठने पर हल्का चक्कर।"
            )
        )
    )

    val sampleWithUncertainty = PrescriptionRecord(
        id = "rx_sample_uncertain",
        title = "Clinic Rx - Verification Safety Alert",
        doctorName = "Dr. S. Nair, MD Pulmonology",
        clinicOrHospital = "Metro Chest Care Clinic",
        patientName = "Ms. Priya V.",
        scanDate = System.currentTimeMillis() - 86400000L * 1, // 1 day ago
        imageUri = null,
        sampleDrawableResId = R.drawable.rx_sample_sharma_1794490611866,
        overallConfidence = 78,
        hasUncertainWords = true,
        generalAdviceEn = "Caution: One handwritten line has unclear cursive script. Must be verified by pharmacist before purchase.",
        generalAdviceKn = "ಎಚ್ಚರಿಕೆ: ಒಂದು ಔಷಧಿಯ ಹೆಸರು ಅಸ್ಪಷ್ಟವಾಗಿದೆ. ಖರೀದಿಸುವ ಮುನ್ನ ಫಾರ್ಮಸಿಸ್ಟ್ ಅಥವಾ ವೈದ್ಯರಿಂದ ಖಚಿತಪಡಿಸಿಕೊಳ್ಳಿ.",
        generalAdviceHi = "सावधानी: एक दवाई की लिखावट अस्पष्ट है। खरीदने से पहले फार्मासिस्ट या डॉक्टर से जांच कराएं।",
        medicines = listOf(
            MedicineItem(
                id = "med_u1",
                name = "Azithromycin 500mg",
                genericName = "Azithromycin",
                dosage = "500 mg",
                frequency = "1 tab OD (Once Daily)",
                timing = "1 hr before lunch / ಊಟಕ್ಕೆ ಮುಂಚೆ",
                duration = "3 Days / ೩ ದಿನಗಳು",
                originalHandwrittenSnippet = "Azithromycin 500mg OD x 3d",
                confidenceScore = 94,
                confidenceLevel = ConfidenceLevel.HIGH,
                verificationStatus = VerificationStatus.VERIFIED_HIGH_CONFIDENCE,
                purposeSummaryEn = "Broad spectrum antibiotic for bronchial infection.",
                purposeSummaryKn = "ಶ್ವಾಸನಾಳದ ಬ್ಯಾಕ್ಟೀರಿಯಾ ಸೋಂಕು ನಿವಾರಣೆಗೆ ಆಂಟಿಬಯೋಟಿಕ್.",
                purposeSummaryHi = "फेफड़ों और ब्रोंकाइटिस के संक्रमण के लिए।",
                precautionsEn = "Strictly take for 3 days as directed.",
                precautionsKn = "೩ ದಿನಗಳ ಕಾಲ ತಪ್ಪದೆ ಸೇವಿಸಿ.",
                precautionsHi = "3 दिन तक लगातार लें।",
                sideEffectsEn = "Stomach cramps.",
                sideEffectsKn = "ಹೊಟ್ಟೆ ಸೆಳೆತ.",
                sideEffectsHi = "पेट दर्द।"
            ),
            MedicineItem(
                id = "med_u2",
                name = "Unclear Rx [Poss. Cpm / Cetirizine 10mg?]",
                genericName = "Unidentified Antiallergic / Bronchodilator",
                dosage = "Uncertain (10mg?)",
                frequency = "At bedtime (?)",
                timing = "Needs verification / ಪರಿಶೀಲನೆ ಅಗತ್ಯವಿದೆ",
                duration = "Needs verification",
                originalHandwrittenSnippet = "Tab C.... 10mg (?) hs",
                confidenceScore = 52,
                confidenceLevel = ConfidenceLevel.LOW,
                verificationStatus = VerificationStatus.NEEDS_DOCTOR_VERIFICATION,
                needsVerificationWarning = "MEDICAL SAFETY ALERT: Hand-written cursive characters are ambiguous. AGNIVA MED AI does not guess medications. Doctor or licensed pharmacist verification required before dispensing.",
                purposeSummaryEn = "Suspected antiallergic syrup or tablet, but cannot be confirmed safely without pharmacist verification.",
                purposeSummaryKn = "ಕೈಬರಹ ಅಸ್ಪಷ್ಟವಾಗಿದೆ. ಅಗ್ನಿವಾ ಮೆಡ್ ಯಾವುದೇ ಊಹೆ ಮಾಡುವುದಿಲ್ಲ. ದಯವಿಟ್ಟು ಔಷಧಾಲಯದಲ್ಲಿ ತೋರಿಸಿ ಖಚಿತಪಡಿಸಿಕೊಳ್ಳಿ.",
                purposeSummaryHi = "लिखावट अस्पष्ट है। सुरक्षा हेतु इस दवा को फार्मासिस्ट को दिखाकर ही लें।",
                precautionsEn = "DO NOT INGEST OR PURCHASE until physically verified by pharmacist.",
                precautionsKn = "ವೈದ್ಯರು ಅಥವಾ ಫಾರ್ಮಸಿಸ್ಟ್ ಖಚಿತಪಡಿಸುವವರೆಗೆ ಈ ಔಷಧಿಯನ್ನು ಸೇವಿಸಬೇಡಿ.",
                precautionsHi = "पुष्टि होने से पहले इस दवाई का सेवन न करें।",
                sideEffectsEn = "Unknown pending verification.",
                sideEffectsKn = "ಮಾಹಿತಿ ಲಭ್ಯವಿಲ್ಲ.",
                sideEffectsHi = "सत्यापन आवश्यक।"
            )
        )
    )

    val allSamples = listOf(sampleSharma, sampleRao, sampleWithUncertainty)
}
