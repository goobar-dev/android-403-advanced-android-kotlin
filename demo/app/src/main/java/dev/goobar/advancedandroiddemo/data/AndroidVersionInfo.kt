package dev.goobar.advancedandroiddemo.data

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelable
import androidx.versionedparcelable.VersionedParcelize
import dev.goobar.advancedandroiddemo.home.NavigationArgs
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@VersionedParcelize
data class AndroidVersionInfo(
    val apiVersion: Int,
    val publicName: String,
    val codename: String,
    val details: String
) : VersionedParcelable, Parcelable, NavigationArgs