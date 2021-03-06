package com.jraska.github.client

import com.google.firebase.database.FirebaseDatabase
import com.jraska.github.client.analytics.AnalyticsProperty
import com.jraska.github.client.analytics.EventAnalytics
import com.jraska.github.client.http.HttpComponent
import com.jraska.github.client.logging.CrashReporter
import com.jraska.github.client.push.PushModule
import com.jraska.github.client.settings.SettingsModule
import com.jraska.github.client.users.UserViewModelModule
import com.jraska.github.client.users.UsersDataModule
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@PerApp
@Component(modules = [UsersDataModule::class, UserViewModelModule::class, NavigationModule::class,
  PushModule::class, AppModule::class, SettingsModule::class,
  HttpComponentModule::class, CoreComponentModule::class])
interface AppComponent {
  fun inject(app: GitHubClientApp)
}

@Module
class HttpComponentModule(private val httpComponent: HttpComponent) {
  @Provides
  fun retrofit(): Retrofit {
    return httpComponent.retrofit()
  }
}

@Module
class CoreComponentModule(private val coreComponent: CoreComponent) {
  @Provides
  fun crashReporter(): CrashReporter {
    return coreComponent.crashReporter()
  }

  @Provides
  fun config(): Config {
    return coreComponent.config()
  }

  @Provides
  fun analyticsProperty(): AnalyticsProperty {
    return coreComponent.analyticsProperty()
  }

  @Provides
  fun analytics(): EventAnalytics {
    return coreComponent.analytics()
  }

  @Provides
  fun firebaseDatabase(): FirebaseDatabase {
    return coreComponent.firebaseDatabase()
  }
}
