package dev.goobar.advancedandroiddemo

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
@VersionedParcelize
data class AndroidVersionInfo(
    val apiVersion: Int,
    val publicName: String,
    val codename: String,
    val details: String
): VersionedParcelable, Parcelable