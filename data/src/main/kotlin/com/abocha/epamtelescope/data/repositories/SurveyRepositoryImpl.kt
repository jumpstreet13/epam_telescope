package com.abocha.epamtelescope.data.repositories

import android.content.Context
import com.abocha.epamtelescope.data.R
import com.abocha.epamtelescope.entities.Survey
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.repository.SurveyRepository
import io.reactivex.Single
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor(private val context: Context) : SurveyRepository {

    override fun getSurveys(): Single<List<Survey>> {
        return Single.fromCallable {
            listOf(
                Survey(
                    context.getString(R.string.all_overall_paltform_photos),
                    SurveyType.OVERALL_PLATFORM_PHOTOS
                ),
                Survey(context.getString(R.string.all_component_title), SurveyType.COMPONENT),
                Survey(
                    context.getString(R.string.all_conductor_wellhead),
                    SurveyType.CONDUCTOR_AND_WELLHEAD
                ),
                Survey(context.getString(R.string.all_riser_title), SurveyType.RISER),
                Survey(context.getString(R.string.all_riser_clamps), SurveyType.RISER_CLAMPS),
                Survey(
                    context.getString(R.string.all_cathodic_protection),
                    SurveyType.CATHODIC_PROTECTION
                ),
                Survey(context.getString(R.string.all_isims), SurveyType.ISIMS),
                Survey(
                    context.getString(R.string.all_cp_calibration),
                    SurveyType.CATHODIC_PROTECTION_CALIBRATION
                )
            )
        }
    }
}
