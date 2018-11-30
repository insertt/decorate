package github.cweijan.decorate.init;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.WindowManagerListener;
import github.cweijan.decorate.core.DecorateModeSwitcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DecorateIniaializer implements ApplicationComponent{

    DecorateIniaializer(){

        List<Integer> already=new ArrayList<>();

        DecorateModeSwitcher modeSwitcher = new DecorateModeSwitcher();

        WindowManager.getInstance().addListener(new WindowManagerListener(){
            @Override
            public void frameCreated(@NotNull IdeFrame ideFrame){

                if(already.contains(ideFrame.hashCode()))return;
                modeSwitcher.decorate(ideFrame);
                already.add(ideFrame.hashCode());

            }

            @Override
            public void beforeFrameReleased(@NotNull IdeFrame ideFrame){
            }
        });
    }

}
