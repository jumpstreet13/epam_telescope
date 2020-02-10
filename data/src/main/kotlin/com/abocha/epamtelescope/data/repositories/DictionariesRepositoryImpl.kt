package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.DictionaryDao
import com.abocha.epamtelescope.data.database.entities.DictionariesEntity
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.data.network.models.responses.DictionariesResponse
import com.abocha.epamtelescope.data.network.models.responses.toDomainModel
import com.abocha.epamtelescope.data.network.services.MainService
import com.abocha.epamtelescope.entities.Dictionaries
import com.abocha.epamtelescope.entities.DictionaryType
import com.abocha.epamtelescope.repository.DictionariesRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DictionariesRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val dictionaryDao: DictionaryDao
) : DictionariesRepository {

    override fun getDictionary(): Observable<Dictionaries> =
        dictionaryDao
            .streamDictionary()
            .map {
                it.first().toDomainModel()
            }
            .toObservable()

    override fun refresh(): Completable =
        mainService.getDictionaries()
            .doOnSuccess(this::save)
            .ignoreElement()

    private fun save(response: DictionariesResponse) {
        response.apply {
            dictionaryDao.upsert(DictionariesEntity(
                id = 0,
                clampTypeEntity = this.clampType.orEmpty().map { it.toDomainModel(DictionaryType.CLAMP) },
                coatingConditionType = this.coatingConditionTypeResponse.orEmpty().map {
                    it.toDomainModel(
                        DictionaryType.COATING_CONDITION
                    )
                },
                componentType = this.componentTypeResponse.orEmpty().map {
                    it.toDomainModel(
                        DictionaryType.COMPONENT
                    )
                },
                conductorAreaType = this.conductorAreaTypeResponse.orEmpty().map {
                    it.toDomainModel(
                        DictionaryType.CONDUCTOR_AREA
                    )
                },
                mechanicalDamageType = this.mechanicalDamageTypeResponse.orEmpty().map {
                    it.toDomainModel(
                        DictionaryType.MECHANICAL_DAMAGE
                    )
                },
                platformAreaType = this.platformAreaTypeResponse.orEmpty().map {
                    it.toDomainModel(
                        DictionaryType.PLATFORM_AREA
                    )
                },
                platformPhotoType = this.platformPhotoTypeResponse.orEmpty().map {
                    it.toDomainModel(
                        DictionaryType.PLATFORM_PHOTO
                    )
                },
                riserType = this.riserTypeResponse.orEmpty().map {
                    it.toDomainModel(
                        DictionaryType.RISER
                    )
                },
                sideType = this.sideTypeResponse.orEmpty().map {
                    it.toDomainModel(
                        DictionaryType.SIDE
                    )
                }
            ))
        }
    }
}
