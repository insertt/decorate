package github.cweijan.decorate.init;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.WindowManagerListener;
import github.cweijan.decorate.core.DecorateModeSwitcher;
import org.jetbrains.annotations.NotNull;

public class DecorateIniaializer implements ApplicationComponent{

    DecorateIniaializer(){

        DecorateModeSwitcher modeSwitcher = new DecorateModeSwitcher();

        WindowManager.getInstance().addListener(new WindowManagerListener(){
            @Override
            public void frameCreated(@NotNull IdeFrame ideFrame){

                modeSwitcher.update(ideFrame);

            }

            @Override
            public void beforeFrameReleased(@NotNull IdeFrame ideFrame){
            }
        });
    }

}
